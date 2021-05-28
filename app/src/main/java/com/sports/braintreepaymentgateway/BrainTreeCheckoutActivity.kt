package com.sports.braintreepaymentgateway

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.braintreepayments.api.dropin.DropInRequest
import com.braintreepayments.api.dropin.DropInResult

class BrainTreeCheckoutActivity : AppCompatActivity(){
    var REQUEST_CODE_PAYPAL_CHECKOUT = 100
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
        proceed!!.text = "Proceed"
        val payment:String = intent.getStringExtra("donation").toString()
        addPayment!!.text = Editable.Factory.getInstance().newEditable(payment)

        proceed!!.setOnClickListener {
           processPayment()
        }
    }


    fun processPayment() {
        val dropInRequest: DropInRequest = DropInRequest()
            .clientToken(getString(R.string.braintree_client_token))
            .requestThreeDSecureVerification(true)
            .collectDeviceData(true)
        startActivityForResult(dropInRequest.getIntent(this), REQUEST_CODE_PAYPAL_CHECKOUT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PAYPAL_CHECKOUT) {
            if (resultCode == RESULT_OK) {
                val result: DropInResult = data!!.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT)!!
                val nonce = result!!.paymentMethodNonce!!.nonce
                Log.d("Nonce", "Nonce Id : " + result!!.paymentMethodNonce!!.nonce)
                Log.d("Nonce", "Nonce Id : " + nonce)

            } else if (resultCode == RESULT_CANCELED) Toast.makeText(
                this,
                 getString(R.string.donation_cancel),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}