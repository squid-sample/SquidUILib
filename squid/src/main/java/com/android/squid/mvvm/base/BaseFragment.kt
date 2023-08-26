package com.android.squid.mvvm.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.squid.mvvm.databinding.DataBindingFragment

abstract class BaseFragment : DataBindingFragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(this.javaClass.simpleName, "onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(this.javaClass.simpleName, "onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(this.javaClass.simpleName, "onCreateView()")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(this.javaClass.simpleName, "onViewCreated()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(this.javaClass.simpleName, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(this.javaClass.simpleName, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(this.javaClass.simpleName, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(this.javaClass.simpleName, "onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(this.javaClass.simpleName, "onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(this.javaClass.simpleName, "onDestroy()")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d(this.javaClass.simpleName, "onHiddenChanged()$hidden")
    }
}