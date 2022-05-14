package com.tsybulnik.shoppingsumin.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tsybulnik.shoppingsumin.domain.ShopItem
import com.tsybulnik.shoppingsumin.domain.ShopListRepository

// по сути синглотон
object ShopListRepositoryIml : ShopListRepository {


//Добавили sortedSetof - чтобы правильно сортировать shopitem
    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private var autoIncrementID = 0

    init {
        for(i in 0..10){
            val item = ShopItem(name = "Name $i",i, true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementID
            autoIncrementID++
        }

        shopList.add(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)

    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }
            ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        // toList - для копии объекта
        return shopListLD

    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}