package com.example.bookingcardriver.common

import android.app.Activity

interface ManageableFragment {
    /**
     * Called when this fragment is put at top of stack.
     */
    fun onFragmentUp(activity: Activity?)

    /**
     * Called when this fragment is remove from top of stack.
     */
    fun onFragmentDown(activity: Activity?)

    fun shouldHandleBackKey(): Boolean

    fun onFragmentBackPressed()
}