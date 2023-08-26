package com.android.squid.mvvm.base

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.squid.mvvm.databinding.DataBindingActivity
import com.android.squid.mvvm.squid.IWindowConfig
import mega.log.MLog

abstract class BaseActivity : DataBindingActivity(), IWindowConfig {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MLog.d(this.javaClass.simpleName, "onCreate --> ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        MLog.d(this.javaClass.simpleName, "onSaveInstanceState --> ")
    }

    override fun onStart() {
        super.onStart()
        MLog.d(this.javaClass.simpleName, "onStart --> ")
    }

    override fun onResume() {
        super.onResume()
        MLog.d(this.javaClass.simpleName, "onResume --> ")
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        MLog.d(this.javaClass.simpleName, "onAttachFragment --> ")
    }

    override fun onRestart() {
        super.onRestart()
        MLog.d(this.javaClass.simpleName, "onRestart --> ")
    }

    override fun onPause() {
        super.onPause()
        MLog.d(this.javaClass.simpleName, "onPause --> ")
    }

    override fun onStop() {
        super.onStop()
        MLog.d(this.javaClass.simpleName, "onStop --> ")
    }

    override fun onDestroy() {
        super.onDestroy()
        MLog.d(this.javaClass.simpleName, "onDestroy --> ")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        MLog.d(this.javaClass.simpleName, "onWindowFocusChanged --> ")
        onConfigWindow()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        MLog.d(this.javaClass.simpleName, "onNewIntent --> ")
    }

    override fun onConfigWindow() {
        val window = window
        if (window != null) MLog.d(this.javaClass.simpleName, "onConfigWindow --> ")
    }
}