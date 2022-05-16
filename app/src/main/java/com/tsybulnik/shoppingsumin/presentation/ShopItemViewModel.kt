package com.tsybulnik.shoppingsumin.presentation

import androidx.lifecycle.ViewModel
import com.tsybulnik.shoppingsumin.data.ShopListRepositoryIml
import com.tsybulnik.shoppingsumin.domain.AddShopItemUseCase
import com.tsybulnik.shoppingsumin.domain.EditShopItemUseCase
import com.tsybulnik.shoppingsumin.domain.GetShopItemUseCase
import com.tsybulnik.shoppingsumin.domain.ShopItem
import java.lang.RuntimeException

class ShopItemViewModel() :ViewModel(){

    private val repository = ShopListRepositoryIml

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val editShopItemUseCase  =EditShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)

    fun getShopItem(shopItemId:Int){
        val item = getShopItemUseCase.getShopUItem(shopItemId)
    }
    fun addShopItem(inputName:String?, inputCount:String?){
        val name = parseName(inputName)

        val count = parseCount(inputCount)
        val fieldValid = validateInput(name,count)
        if (fieldValid){
            val shopItem = ShopItem(name,count,true)
            addShopItemUseCase.addShopItem(shopItem)
        }

    }
    fun editShopItem(inputName:String?, inputCount:String?){
        val name = parseName(inputName)

        val count = parseCount(inputCount)
        val fieldValid = validateInput(name,count)
        if (fieldValid){
            val shopItem = ShopItem(name,count,true)
            editShopItemUseCase.editShopItem(shopItem)
        }
    }

    private fun parseName(inputName:String?):String{
        return inputName?.trim() ?:""
    }

    private fun parseCount(inputCount: String?):Int{
        return try {
            inputCount?.trim()?.toInt() ?: 0
        }catch (e:Exception){
            0
        }
    }

    private fun validateInput(name:String, count:Int):Boolean{
        var result = true
        if(name.isBlank()){
            //TODO() - show error input name
            result = false
        }
        if(count<=0){
            // TODO() - show error input count
        //  result = false
        }
        return result
    }
}