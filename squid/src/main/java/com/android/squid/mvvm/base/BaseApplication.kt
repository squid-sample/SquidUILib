package com.android.squid.mvvm.base

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

open class BaseApplication : Application(), ViewModelStoreOwner {
    private lateinit var mAppViewModelStore: ViewModelStore
    private var mFactory: ViewModelProvider.Factory? = null

    companion object {
        lateinit var INSTANCE: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        mAppViewModelStore = ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }
}