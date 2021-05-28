package com.sports.braintreepaymentgateway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    var addPayment : EditText? = null
    var proceed : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setValues()
    }

    fun setValues(){
        addPayment = findViewById(R.id.addPayment)
        proceed = findViewById(R.id.proceed)

        proceed!!.setOnClickListener {
            if(addPayment!!.text != null && addPayment!!.text.length > 0){
                startActivity(Intent(this,BrainTreeCheckoutActivity::class.java).putExtra("donation",addPayment!!.text.toString()))
            }
        }
    }
}