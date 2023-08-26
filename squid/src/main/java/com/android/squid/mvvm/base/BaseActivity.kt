package com.android.squid.mvvm.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.android.squid.mvvm.databinding.DataBindingActivity
import com.android.squid.mvvm.squid.IWindowConfig

abstract class BaseActivity : DataBindingActivity(), IWindowConfig {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(this.javaClass.simpleName, "onCreate --> ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(this.javaClass.simpleName, "onSaveInstanceState --> ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(this.javaClass.simpleName, "onStart --> ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(this.javaClass.simpleName, "onResume --> ")
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        Log.d(this.javaClass.simpleName, "onAttachFragment --> ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(this.javaClass.simpleName, "onRestart --> ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(this.javaClass.simpleName, "onPause --> ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(this.javaClass.simpleName, "onStop --> ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(this.javaClass.simpleName, "onDestroy --> ")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d(this.javaClass.simpleName, "onWindowFocusChanged --> ")
        onConfigWindow()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(this.javaClass.simpleName, "onNewIntent --> ")
    }

    override fun onConfigWindow() {
        val window = window
        if (window != null) Log.d(this.javaClass.simpleName, "onConfigWindow --> ")
    }
}