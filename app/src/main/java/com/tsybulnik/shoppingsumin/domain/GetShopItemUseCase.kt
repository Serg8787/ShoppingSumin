package com.tsybulnik.shoppingsumin.domain

import javax.inject.Inject

class GetShopItemUseCase@Inject constructor(
    private val shopListRepository: ShopListRepository) {
    suspend fun getShopUItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}