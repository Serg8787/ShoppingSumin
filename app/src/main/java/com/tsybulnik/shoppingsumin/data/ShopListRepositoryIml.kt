package com.tsybulnik.shoppingsumin.data

import com.tsybulnik.shoppingsumin.domain.ShopItem
import com.tsybulnik.shoppingsumin.domain.ShopListRepository

// по сути синглотон
object ShopListRepositoryIml : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementID = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementID
            autoIncrementID++
        }

        shopList.add(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }
            ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): List<ShopItem> {
        // toList - для копии объекта
        return shopList.toList()

    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }
}