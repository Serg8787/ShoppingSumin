package com.tsybulnik.shoppingsumin.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.tsybulnik.shoppingsumin.domain.ShopItem
import com.tsybulnik.shoppingsumin.domain.ShopListRepository
import java.util.*

class ShopListRepositoryIml(
    application: Application
) : ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDAO()
    private val mapper = ShopListMapper()



    override fun addShopItem(shopItem: ShopItem) {
       shopListDao.addShopItem(mapper.mapEntityToDBModel(shopItem))
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDBModel(shopItem))

    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDBModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        // toList - для копии объекта
        return shopListDao.getShopList()

    }

    override fun deleteShopItem(shopItem: ShopItem) {
      shopListDao.deleteShopItem(shopItem.id)
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}