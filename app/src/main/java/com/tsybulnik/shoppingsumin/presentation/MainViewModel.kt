package com.tsybulnik.shoppingsumin.presentation

import android.app.Application
import androidx.lifecycle.*
import com.tsybulnik.shoppingsumin.data.ShopListRepositoryIml
import com.tsybulnik.shoppingsumin.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(

    private val getShopListUseCase :GetShopListUseCase,
            private val deleteShopItemUseCase : DeleteShopItemUseCase,
            private val editShopItemUseCase : EditShopItemUseCase

  ) : ViewModel(){

    val shopList = getShopListUseCase.getShopLIst()

    fun deleteShopItem(shopItem: ShopItem){
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }
    fun changeEnabledState(shopItem: ShopItem){
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}