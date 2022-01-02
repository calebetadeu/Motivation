package com.example.motivation.Infra

import android.content.Context
import android.text.Editable


class SecurityPreferences( context: Context) {
    private val mSharedPreferences=
        context.getSharedPreferences("motivation",Context.MODE_PRIVATE)

    fun storeUser(key:String,value:String){
        mSharedPreferences.edit().putString(key,value).apply()
    }
    fun getUser(key:String):String{
          return (mSharedPreferences.getString(key,"") ?:"")
    }
}