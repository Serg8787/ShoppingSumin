package com.tsybulnik.shoppingsumin.domain

data class ShopItem(

    val name:String,
    val count:Int,
    val enabled:Boolean,
    var id:Int = UNDEFINED_ID,
)
{
    companion object{
        // не определенный id
         const val UNDEFINED_ID = 0
    }
}
