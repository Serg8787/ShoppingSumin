package com.tsybulnik.shoppingsumin.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetShopListUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository) {
    fun getShopLIst(): LiveData<List<ShopItem>>{
       return shopListRepository.getShopList()
    }
}