package com.example.bookingcardriver.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.bookingcardriver.R
import com.example.bookingcardriver.databinding.ActivityIdentityCardBinding

class IdentityCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIdentityCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_identity_card)
        eventHandle()
    }

    private fun eventHandle() {
       binding.buttonSaveIdentityCard.setOnClickListener {
           val intent = Intent(this, RegisterActivity::class.java)
           startActivity(intent)
       }
    }

}