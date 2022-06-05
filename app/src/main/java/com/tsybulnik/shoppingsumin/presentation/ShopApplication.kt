package com.tsybulnik.shoppingsumin.presentation

import android.app.Application
import com.tsybulnik.shoppingsumin.di.DaggerApplicationComponent

class ShopApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}