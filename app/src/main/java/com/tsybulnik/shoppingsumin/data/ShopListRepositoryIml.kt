package com.tsybulnik.shoppingsumin.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.tsybulnik.shoppingsumin.domain.ShopItem
import com.tsybulnik.shoppingsumin.domain.ShopListRepository
import java.util.*
import javax.inject.Inject

class ShopListRepositoryIml @Inject constructor(
    private val shopListDao: ShopListDAO,
    private val mapper: ShopListMapper
) : ShopListRepository {




    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDBModel(shopItem))
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDBModel(shopItem))

    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDBModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> =
        Transformations.map(shopListDao.getShopList()) {
            mapper.mapListDbModelToListEntity(it)
        }


    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

//    private fun updateList() {
//        shopListLD.value = shopList.toList()
//    }
}