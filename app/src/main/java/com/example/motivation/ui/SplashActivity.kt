package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.Infra.MotivationConstants
import com.example.motivation.Infra.SecurityPreferences
import com.example.motivation.R
import com.example.motivation.databinding.ActivitySplashBinding
import kotlinx.android.synthetic.main.activity_splash.*

//Intent:iniciar uma outra activity
//shared preferences : Dados pequenos,acesso rapido, valores que não mudam muitos
class SplashActivity : AppCompatActivity(),View.OnClickListener {


    private lateinit var mSecurityPreferences:SecurityPreferences //lateinit:Quando ainda não tem um valor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mSecurityPreferences= SecurityPreferences((this)) //o Contexto apos o OnCreate
        userName.setOnClickListener(this)
        if(supportActionBar!=null){
            supportActionBar!!.hide()
        }
        buttonSave.setOnClickListener(this)

        verifyName()

    }


    override fun onClick(view: View?) {
         val id= view?.id

        if (id == R.id.buttonSave){
           handleSave()
    }
}
    private fun verifyName(){
        val name=mSecurityPreferences.getUser(MotivationConstants.KEY.PERSON_NAME)
        if(name !=""){
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun handleSave(){
        val name= userName.text.toString()
        if(name !=""){
            mSecurityPreferences.storeUser(MotivationConstants.KEY.PERSON_NAME,name)
            val intent= Intent(this, MainActivity::class.java )//Intent:iniciar uma outra activity
                startActivity(intent)
            finish()
        }else{
            Toast.makeText(this,"Informe seu Nome",Toast.LENGTH_SHORT).show()
        }
    }


}
