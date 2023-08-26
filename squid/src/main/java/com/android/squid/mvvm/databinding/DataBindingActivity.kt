package com.android.squid.mvvm.databinding

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.squid.mvvm.base.BaseApplication
import com.android.squid.mvvm.squid.IPhone

abstract class DataBindingActivity : AppCompatActivity(), IPhone {
    private var mActivityProvider: ViewModelProvider? = null
    private var mFactory: ViewModelProvider.Factory? = null
    private var mBinding: ViewDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVm()
        val dataBindingConfig = createConfigs()
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            dataBindingConfig.mLayout
        )
        binding.lifecycleOwner = this
        val bindingParams = dataBindingConfig.mParams
        for (i in 0 until bindingParams.size()) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
        }
        this.mBinding = binding
        loadData()
        observerData()
    }

    protected fun <T : ViewModel> getActivityViewModel(clazz: Class<T>): T {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider!![clazz]
    }

    protected fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(
            (this.applicationContext as BaseApplication),
            getAppFactory(this)!!
        )
    }

    private fun getAppFactory(activity: Activity): ViewModelProvider.Factory? {
        val application: Application = checkApplication(activity)
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        }
        return mFactory
    }

    private fun checkApplication(activity: Activity): Application {
        return activity.application
            ?: throw IllegalStateException(
                "Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModel before onCreate call."
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding!!.unbind()
        mBinding = null
    }
}