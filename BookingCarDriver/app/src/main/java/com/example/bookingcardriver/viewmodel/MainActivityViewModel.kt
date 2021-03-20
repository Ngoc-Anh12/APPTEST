package com.example.bookingcardriver.viewmodel

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import com.example.bookingcardriver.R

class MainActivityViewModel : ViewModel(){
    fun onNavigationItemSelected(item: MenuItem) :Boolean{
        when(item.itemId){
            R.id.ic_home -> {
                
                return true
            }
            R.id.ic_search -> {

                return true
            }
            R.id.ic_notification -> {

                return true
            }
            R.id.ic_account -> {

                return true
            }
        }
        return false
    }
}