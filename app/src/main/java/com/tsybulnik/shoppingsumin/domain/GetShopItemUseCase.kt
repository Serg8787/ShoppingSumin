package com.tsybulnik.shoppingsumin.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun getShopUItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}