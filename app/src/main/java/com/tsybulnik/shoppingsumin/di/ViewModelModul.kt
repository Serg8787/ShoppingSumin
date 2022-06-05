package com.tsybulnik.shoppingsumin.di

import androidx.lifecycle.ViewModel
import com.tsybulnik.shoppingsumin.presentation.MainViewModel
import com.tsybulnik.shoppingsumin.presentation.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModul {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel:MainViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    fun bindShopItemViewModel(viewModel:ShopItemViewModel):ViewModel


}