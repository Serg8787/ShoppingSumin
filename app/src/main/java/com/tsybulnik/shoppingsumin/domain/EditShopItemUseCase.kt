package com.tsybulnik.shoppingsumin.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem){

        shopListRepository.editShopItem(shopItem)

    }
}