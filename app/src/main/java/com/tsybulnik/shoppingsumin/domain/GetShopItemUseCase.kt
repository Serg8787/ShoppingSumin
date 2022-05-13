package com.tsybulnik.shoppingsumin.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopUItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}