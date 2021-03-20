package com.example.bookingcardriver

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.bookingcardriver.account.AccountFragment
import com.example.bookingcardriver.common.FragmentManagerHelper
import com.example.bookingcardriver.databinding.ActivityMainBinding
import com.example.bookingcardriver.home.HomeMapFragment
import com.example.bookingcardriver.notification.NotificationFragment
import com.example.bookingcardriver.search.SearchFragment
import com.example.bookingcardriver.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var activityViewModel: MainActivityViewModel
    private val TAB_HOME: Int = 0
    private val TAB_SEARCH: Int = 1
    private val TAB_PERSONAL: Int = 3
    private val TAB_NOTIFICATION: Int = 2

    private lateinit var fragmentList: MutableList<Fragment>
    private lateinit var backStack: MutableList<Int>
    private var mTabSelected = booleanArrayOf(false, false, false, false, false)
    private var mTabAdded = booleanArrayOf(false, false, false, false, false)
    private lateinit var fragmentManagerHelper: FragmentManagerHelper
    private lateinit var homeMapFragment: HomeMapFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var notificationFragment: NotificationFragment
    private lateinit var accountFragment: AccountFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        backStack = ArrayList()
        setUpFragment()
        initView()

    }


    private fun initView() {
        onUpdateFooter(TAB_HOME, false)
        footerNavigate()
        headerNavigate()
    }

    private fun onUpdateFooter(tab: Int, addToBackStack: Boolean) {
        if (mTabSelected[tab]){
            return
        }
        var previousTab = -1
        for(i in 0..3){
            if (mTabSelected[i]){
                previousTab = i
            }
            mTabSelected[i] = false
        }
        mTabSelected[tab] = true
        binding.commonFooter.imgHomeIc.setImageResource(if (mTabSelected[TAB_HOME]) R.drawable.ic_baseline_home_24 else R.drawable.home_ic_new)
        binding.commonFooter.imgSearchIc.setImageResource(if (mTabSelected[TAB_SEARCH]) R.drawable.ic_baseline_search_24 else R.drawable.search_ic)
        binding.commonFooter.imgNotificationIc.setImageResource(if (mTabSelected[TAB_NOTIFICATION]) R.drawable.ic_baseline_notifications_none_24 else R.drawable.noti_ic_new)
        binding.commonFooter.imgSettingIc.setImageResource(if (mTabSelected[TAB_PERSONAL]) R.drawable.ic_outline_settings_24 else R.drawable.setting_ic_new)
        if (mTabSelected[TAB_HOME]){
            binding.commonHeader.visibility = View.VISIBLE
        }
        else{
            binding.commonHeader.visibility = View.GONE
        }
        fragmentManagerHelper.showFragment(tab)
        mTabAdded[tab] = true
        if (addToBackStack && previousTab != -1){
            addToBackStack(previousTab)
        }
    }
    fun addToBackStack(tab: Int){
        val size = backStack.size
        for (i in 0 until size) {
            if (backStack[i] == tab) {
                backStack.removeAt(i)
                break
            }
        }
        if (backStack.size >= 5) {
            backStack.removeAt(0)
        }
        backStack.add(tab)
    }
    private fun footerNavigate(){
        binding.commonFooter.flFooterHome.setOnClickListener {
            onUpdateFooter(TAB_HOME, true)
        }
        binding.commonFooter.flFooterSearch.setOnClickListener {
            onUpdateFooter(TAB_SEARCH, true)
        }
        binding.commonFooter.flFooterNotification.setOnClickListener {
            onUpdateFooter(TAB_NOTIFICATION, true)
        }
        binding.commonFooter.flFooterSetting.setOnClickListener {
            onUpdateFooter(TAB_PERSONAL, true)
        }

    }

    private fun headerNavigate(){

    }
    private fun setUpFragment(){
        fragmentList = ArrayList()
        fragmentManagerHelper = FragmentManagerHelper(this, R.id.fl_home_body)
        //home map fragment
        homeMapFragment = HomeMapFragment()
        (fragmentList as ArrayList<Fragment>).add(homeMapFragment)
        fragmentManagerHelper.onAttachFragment(homeMapFragment)
        //search fragment
        searchFragment = SearchFragment()
        (fragmentList as ArrayList<Fragment>).add(searchFragment)
        fragmentManagerHelper.onAttachFragment(searchFragment)
        //notification fragment
        notificationFragment = NotificationFragment()
        (fragmentList as ArrayList<Fragment>).add(notificationFragment)
        fragmentManagerHelper.onAttachFragment(notificationFragment)
        //account fragment
        accountFragment = AccountFragment()
        (fragmentList as ArrayList<Fragment>).add(accountFragment)
        fragmentManagerHelper.onAttachFragment(accountFragment)
        fragmentManagerHelper.onCreate()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragments = supportFragmentManager.fragments
        if (fragments != null){
            for (fragment in fragments) {
                fragment.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    override fun onBackPressed() {
        var selectedTab = 0
        for (t in 0..mTabSelected.size){
            if (mTabSelected[t]){
                selectedTab = t
                break
            }
        }

    }


}