package com.tsybulnik.shoppingsumin.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int):ShopItem


    fun getShopList():List<ShopItem>

    fun deleteShopItem(shopItem: ShopItem)
}