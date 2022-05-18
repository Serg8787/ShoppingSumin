package com.tsybulnik.shoppingsumin.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopLIst(): LiveData<List<ShopItem>>{
       return shopListRepository.getShopList()
    }
}