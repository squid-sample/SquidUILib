package com.android.squid.mvvm.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.squid.utils.FragmentUtils
import mega.log.MLog

abstract class BaseManagerFragmentActivity : BaseActivity() {
    private var mFragments: Array<Fragment?>? = null
    private var mCurPageIndex = -1

    companion object {
        const val KEY_PAGE_INDEX = "com.mega.BaseManageFragmentActivity.pageIndex"
    }

    override fun onAttachFragment(fragment: Fragment) {
        restoreFragment(fragment)
        super.onAttachFragment(fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initFragment()
        super.onCreate(savedInstanceState)
    }

    private fun initFragment() {
        createFragments()
        for (i in mFragments!!.indices) {
            if (mFragments!![i] == null) {
                mFragments!![i] = onCreateFragment(i)
                if (!mFragments!![i]!!.isAdded) {
                    FragmentUtils.add(supportFragmentManager, mFragments!![i]!!, getContainerId())
                }
            }
        }
    }

    open fun switchPage(pageIndex: Int) {
        try {
            if (pageIndex >= 0 && pageIndex < mFragments!!.size && mCurPageIndex != pageIndex) {
                mCurPageIndex = pageIndex
                val toFragment = mFragments!![pageIndex]
                val fm = supportFragmentManager
                val topShow = FragmentUtils.getTopShow(fm)
                if (toFragment != topShow) {
                    MLog.d(this.javaClass.simpleName, " Switch page index: $pageIndex")
                    if (!toFragment!!.isAdded) {
                        FragmentUtils.add(fm, toFragment, getContainerId())
                    }
                    FragmentUtils.showHide(toFragment, *mFragments!!)
                    fm.executePendingTransactions()
                }
            }
        } catch (e: IllegalStateException) {
            MLog.w(" switch page : $e")
        } catch (e: NullPointerException) {
            MLog.w(" switch page : $e")
        }
    }

    private fun restoreFragment(fragment: Fragment) {
        createFragments()
        val bundle = fragment.arguments
        if (bundle != null) {
            val index = bundle.getInt(KEY_PAGE_INDEX)
            if (mFragments!![index] !== fragment) {
                MLog.d(this.javaClass.simpleName, "Restore index:$index")
                mFragments!![index] = fragment
            }
        }
    }

    private fun createFragments() {
        if (mFragments == null) {
            val maxFragmentCount: Int = getMaxFragmentCount()
            mFragments = arrayOfNulls(maxFragmentCount)
        }
    }

    override fun onDestroy() {
        mFragments = null
        super.onDestroy()
    }

    override fun finish() {
        moveTaskToBack(true)
    }

    abstract fun getMaxFragmentCount(): Int

    abstract fun getContainerId(): Int

    abstract fun onCreateFragment(pageIndex: Int): Fragment?
}