package com.tsybulnik.shoppingsumin.di

import android.app.Activity
import android.app.Application
import com.tsybulnik.shoppingsumin.presentation.MainActivity
import com.tsybulnik.shoppingsumin.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component
@ApplicationScope
@Component(modules = [DataModul::class, ViewModelModul::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragmegt:ShopItemFragment)


    @Component.Factory
    interface Factory{


        fun create(
           @BindsInstance application: Application):ApplicationComponent
    }
}