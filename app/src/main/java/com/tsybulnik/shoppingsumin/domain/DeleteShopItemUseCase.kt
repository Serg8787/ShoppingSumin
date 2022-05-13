package com.tsybulnik.shoppingsumin.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem){

        shopListRepository.deleteShopItem(shopItem)

    }
}