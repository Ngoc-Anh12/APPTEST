package com.example.bookingcardriver.common

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class FragmentManagerHelper{
    private val TAG = FragmentManagerHelper::class.java.name
    private var mActivity: FragmentActivity? = null
    private var mFragmentManager: FragmentManager? = null
    private val mFragments: MutableList<Fragment> = mutableListOf()
    private val mPendingFragments: MutableList<Fragment> = mutableListOf()
    private var mActivityCreated = false

    private var mContainerId = 0

    constructor(activity: FragmentActivity, containerId: Int){
        mActivity = activity
        mFragmentManager = mActivity!!.supportFragmentManager
        mContainerId = containerId
    }

    fun onCreate(){
        mActivityCreated = true
        if (mPendingFragments.isNotEmpty()){
            for (i:Int in 0..3){
                mFragments.add(mPendingFragments[i])
            }
            mPendingFragments.clear()
            notifyCurrentFragmentUp()
        }
    }
    fun onAttachFragment(fragment: Fragment?) {
        if (!mActivityCreated /*&& fragment instanceof ManageableFragment*/) mPendingFragments.add(fragment!!)
    }
    fun isFragmentHandleBackKey(): Boolean {
        val fragment = getCurrentFragment()
        return if (fragment != null && fragment is ManageableFragment) (fragment as ManageableFragment).shouldHandleBackKey() else false
    }
    fun onBackPressed(finishActivityIfNeed: Boolean): Boolean {
        return if (isFragmentHandleBackKey()) {
            val manageableFragment = getCurrentFragment() as ManageableFragment?
            manageableFragment!!.onFragmentBackPressed()
            true
        } else if (!popBackStack()) {
            if (finishActivityIfNeed) mActivity!!.finish()
            false
        } else true
    }
    private fun notifyCurrentFragmentUp() {
      //  TODO("Not yet implemented")
        val curFragment = getCurrentFragment()
        if (curFragment != null && curFragment is ManageableFragment){
            (curFragment as ManageableFragment).onFragmentUp(mActivity)
        }
    }
    private fun notifyCurrentFragmentDown() {
        val curFragment = getCurrentFragment()
        if (curFragment != null && curFragment is ManageableFragment) (curFragment as ManageableFragment).onFragmentDown(mActivity)
    }
    private fun getCurrentFragment(): Fragment? {
        return getFragmentAt(getBackStackCount() - 1)
    }

    private fun getFragmentAt(index: Int): Fragment? {
        return if (mFragments.isEmpty()) null else mFragments[index]
    }
    fun getBackStackCount(): Int {
        return mFragments.size
    }
    fun popBackStack(): Boolean {
        return getBackStackCount() > 1 && popBackStack(getBackStackCount() -1, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
    fun popBackStack(index: Int, flags: Int): Boolean {
        if (index < 0) return false
        var result = false
        notifyCurrentFragmentDown()
        try {
            mFragmentManager!!.popBackStackImmediate(index, flags)
            result = true
            var i = mFragments.size - 1
            val end = if (flags == FragmentManager.POP_BACK_STACK_INCLUSIVE) index - 1 else index
            while (i > end) {
                mFragments.removeAt(i)
                i--
            }
            notifyCurrentFragmentUp()
        } catch (ex: Exception) {
            Log.e(TAG, "Error pop back stack", ex)
        }
        return result
    }
    fun showFragment(index: Int): Boolean {
        var result = false
        if (index < mFragments.size) {
            val fragment = mFragments[index]
            if (!fragment.isVisible) {
                notifyCurrentFragmentDown()
                val transaction = mFragmentManager!!.beginTransaction()
                transaction.replace(mContainerId, fragment, null)
                //transaction.addToBackStack(null);
                try {
                    transaction.commit()
                    if (fragment is ManageableFragment) {
                        (fragment as ManageableFragment).onFragmentUp(mActivity)
                    }
                } catch (ex: java.lang.Exception) {
                    Log.e(TAG, "Error commit transaction" + ex.message)

                }
            }
            result = true
        }
        return result
    }
    fun showFragment(fragment: Fragment, animEnter: Int, animExit: Int, animPopEnter: Int, animPopExit: Int, addNew: Boolean): Boolean {
        var result = false
        if (!fragment.isVisible) {
            notifyCurrentFragmentDown()
            val transaction = mFragmentManager!!.beginTransaction()
            if (addNew) mFragmentManager!!.popBackStackImmediate(0, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            transaction.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit)
            transaction.replace(mContainerId, fragment, null)
            transaction.addToBackStack(null)
            try {
                transaction.commit()
                result = true
                mFragments.add(fragment)
                notifyCurrentFragmentUp()
            } catch (ex: java.lang.Exception) {
                Log.e(TAG, "Error commit transaction" + ex.message)

            }
        }
        return result
    }
}