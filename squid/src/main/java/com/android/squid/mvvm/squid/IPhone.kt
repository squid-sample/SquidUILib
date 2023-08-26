package com.android.squid.mvvm.squid

import com.android.squid.mvvm.model.DataBindingConfig

/**
 * Time:2023/3/7
 * Author:bin.yan
 * Description:UI层接口
 */
interface IPhone {
    fun initVm()

    fun createConfigs(): DataBindingConfig

    fun loadData(){}

    fun observerData(){}
}