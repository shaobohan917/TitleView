package com.luka.title

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.luka.mylibrary.TitleView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        titleView.setOnTitleClickListener(object : TitleView.OnTitleClickListener {
            override fun onBackClick() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onMoreImgClick() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onMoreImg2Click() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onMoreTextClick() {
                Log.d("luka", "设置")
            }

        })
    }

}
