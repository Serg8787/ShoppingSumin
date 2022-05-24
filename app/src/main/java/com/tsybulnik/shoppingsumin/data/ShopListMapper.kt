package com.tsybulnik.shoppingsumin.data

import com.tsybulnik.shoppingsumin.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDBModel(shopItem: ShopItem):ShopItemDbModel = ShopItemDbModel(id = shopItem.id,
    name = shopItem.name,
    count = shopItem.count,
    enabled = shopItem.enabled)

    fun mapDBModelToEntity(shopItemDbModel: ShopItemDbModel):ShopItem = ShopItem(id = shopItemDbModel.id,
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        enabled = shopItemDbModel.enabled)


    fun mapListDbModelToListEntity(list:List<ShopItemDbModel>)=
        list.map {
            mapDBModelToEntity(it)
        }

}