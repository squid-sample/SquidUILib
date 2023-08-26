package com.android.squid.mvvm.model

import android.util.SparseArray
import androidx.lifecycle.ViewModel

data class DataBindingConfig(val layoutId: Int) {
    var mLayout: Int = layoutId
    var mParams = SparseArray<Any?>()

    constructor(layout: Int, variableId: Int, stateViewModel: ViewModel) : this(layout) {
        this.mLayout = layout
        mParams.put(variableId, stateViewModel)
    }

    fun addParams(variableId: Int, `object`: Any): DataBindingConfig {
        if (mParams[variableId] == null) {
            mParams.put(variableId, `object`)
        }
        return this@DataBindingConfig
    }
}