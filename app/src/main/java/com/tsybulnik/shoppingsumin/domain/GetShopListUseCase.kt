package com.tsybulnik.shoppingsumin.domain

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopLIst():List<ShopItem>{
       return shopListRepository.getShopList()
    }
}