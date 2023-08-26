package com.android.squid.mvvm.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mega.btphone.basic.databinding.DataBindingFragment
import mega.log.MLog

abstract class BaseFragment : DataBindingFragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        MLog.d(this.javaClass.simpleName, "onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MLog.d(this.javaClass.simpleName, "onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MLog.d(this.javaClass.simpleName, "onCreateView()")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MLog.d(this.javaClass.simpleName, "onViewCreated()")
    }

    override fun onStart() {
        super.onStart()
        MLog.d(this.javaClass.simpleName, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        MLog.d(this.javaClass.simpleName, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        MLog.d(this.javaClass.simpleName, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        MLog.d(this.javaClass.simpleName, "onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MLog.d(this.javaClass.simpleName, "onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        MLog.d(this.javaClass.simpleName, "onDestroy()")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        MLog.d(this.javaClass.simpleName, "onHiddenChanged()$hidden")
    }
}