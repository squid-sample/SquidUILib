package com.android.squid.mvvm.squid

import com.android.squid.mvvm.model.DataBindingConfig

interface IPhone {
    fun initVm()

    fun createConfigs(): DataBindingConfig

    fun loadData() {}

    fun observerData() {}
}