package com.android.squid.mvvm.databinding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.squid.mvvm.base.BaseApplication
import com.android.squid.mvvm.squid.IPhone

abstract class DataBindingFragment : Fragment(), IPhone {
    lateinit var mActivity: AppCompatActivity
    lateinit var mBinding: ViewDataBinding
    private var mFragmentProvider: ViewModelProvider? = null
    private var mParentFragmentProvider: ViewModelProvider? = null
    private var mActivityProvider: ViewModelProvider? = null
    private var mApplicationProvider: ViewModelProvider? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVm()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBindingConfig = createConfigs()
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, dataBindingConfig.mLayout,
            container, false
        )
        binding.lifecycleOwner = this.viewLifecycleOwner
        val bindingParams = dataBindingConfig.mParams
        for (i in 0 until bindingParams.size()) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
        }
        mBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        observerData()
    }

    protected fun <T : ViewModel> getFragmentViewModel(clazz: Class<T>): T {
        if (mFragmentProvider == null) {
            mFragmentProvider = ViewModelProvider(this)
        }
        return mFragmentProvider!![clazz]
    }

    protected fun <T : ViewModel> getParentFragmentViewModel(clazz: Class<T>): T? {
        val parentFragment = parentFragment
        if (parentFragment != null) {
            if (mParentFragmentProvider == null) {
                mParentFragmentProvider = ViewModelProvider(parentFragment)
            }
            return mParentFragmentProvider!![clazz]
        }
        return null
    }

    protected fun <T : ViewModel> getActivityViewModel(clazz: Class<T>): T {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(mActivity)
        }
        return mActivityProvider!![clazz]
    }

    protected fun getAppViewModelProvider(): ViewModelProvider? {
        if (mApplicationProvider == null) {
            mApplicationProvider =
                ViewModelProvider((mActivity.applicationContext as BaseApplication))
        }
        return mApplicationProvider
    }
}