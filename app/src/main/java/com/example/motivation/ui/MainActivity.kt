package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.Infra.MotivationConstants
import com.example.motivation.Infra.SecurityPreferences
import com.example.motivation.R
import kotlinx.android.synthetic.main.activity_main.*
import repository.Mock


class MainActivity : AppCompatActivity(), View.OnClickListener  {

    private lateinit var mSecurityPreferences: SecurityPreferences //lateinit:Quando ainda não tem um valor
    private var mPhraseFilter:Int =MotivationConstants.PRHASEFILTER.ALL
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(supportActionBar!=null){
            supportActionBar!!.hide() }


        mSecurityPreferences= SecurityPreferences(this)
        val name=mSecurityPreferences.getUser(MotivationConstants.KEY.PERSON_NAME)
        textName.text ="Olá,${name}"


        // Lógica Inicial de Seleção
        imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhrase()

        buttonNewPhrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id=view.id
        val listFilter=listOf( R.id.imageAll,R.id.imageHappy,R.id.imageMorning )

        if(id== R.id.buttonNewPhrase){
                handleNewPhrase()
        }else if(id in listFilter){
            handleFilter(id)
        }
    }
    private fun handleFilter(id:Int){

        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))
        imageMorning.setColorFilter(resources.getColor(R.color.white))
            when (id){
                R.id.imageAll->{
                    imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                    mPhraseFilter=MotivationConstants.PRHASEFILTER.ALL
                }
                R.id.imageHappy->{
                    imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                    mPhraseFilter=MotivationConstants.PRHASEFILTER.HAPPY
                 }
                R.id.imageMorning->{
                    imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))
                    mPhraseFilter=MotivationConstants.PRHASEFILTER.MORNING
                }
            }
    }
    private fun handleNewPhrase(){
        textPhrase.text = Mock().getPhrase(mPhraseFilter)

    }


}