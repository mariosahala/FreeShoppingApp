package id.mario.core.util

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun ImageView.loadDrawable(drawable: Drawable?, requestOptions: RequestOptions? = null) {
    requestOptions?.let { reqOptions ->
        Glide.with(this).load(drawable).apply(reqOptions).into(this)
    } ?: kotlin.runCatching {
        Glide.with(this).load(drawable).into(this)
    }
}

fun ImageView.loadImage(path: String) {
    Glide.with(this).load(path).into(this)
}

fun Fragment.hideKeyboard(): Boolean {
    return (context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow((activity?.currentFocus ?: View(context)).windowToken, 0)
}

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(),
        message,
        Toast.LENGTH_SHORT).show()
}

// SnackBar
fun Fragment.snackBar() {
    Snackbar.make(View(requireActivity()),"Coming soon!", Snackbar.LENGTH_SHORT).show()
}