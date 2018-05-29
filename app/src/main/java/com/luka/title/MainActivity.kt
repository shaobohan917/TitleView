package com.luka.title

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.luka.library.TitleView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleView.setOnMoreTextClickListener { object :TitleView.OnMoreTextClickListener{
            override fun onMoreTextClick() {

                Log.d("luka","设置")
            }

        } }
    }

}
