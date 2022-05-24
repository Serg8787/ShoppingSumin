package com.tsybulnik.shoppingsumin.presentation

import android.app.Application
import androidx.lifecycle.*
import com.tsybulnik.shoppingsumin.data.ShopListRepositoryIml
import com.tsybulnik.shoppingsumin.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application){
    private val repository = ShopListRepositoryIml(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

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