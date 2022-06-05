package com.tsybulnik.shoppingsumin.di

import android.app.Application
import com.tsybulnik.shoppingsumin.data.AppDatabase
import com.tsybulnik.shoppingsumin.data.ShopListDAO
import com.tsybulnik.shoppingsumin.data.ShopListRepositoryIml
import com.tsybulnik.shoppingsumin.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModul {

    @Binds
    @ApplicationScope
    fun bindShopListRepository(impl: ShopListRepositoryIml): ShopListRepository

    companion object{


        @Provides
        @ApplicationScope
        fun provideShopListDao(application: Application):ShopListDAO{
            return AppDatabase.getInstance(application).shopListDAO()
        }
    }
}