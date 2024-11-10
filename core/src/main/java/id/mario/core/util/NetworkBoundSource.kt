package id.mario.core.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {

    val data = query().first()
    val resource = if (shouldFetch(data)) {
        emit(ApiStates.Loading(data))
        try {
            val resultType = fetch()
            saveFetchResult(resultType)
            query().map { ApiStates.Success(it) }
        } catch (throwable: Throwable) {
            query().map { ApiStates.Error(throwable, it) }
        }
    } else {
        query().map { ApiStates.Success(it) }
    }
    emitAll(resource)
}