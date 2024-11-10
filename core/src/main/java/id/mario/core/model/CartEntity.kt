package id.mario.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = true) val id:Int? = null,
    val image: String,
    val title:String,
    val price:Double,
    val category: String,
    val description: String,
)