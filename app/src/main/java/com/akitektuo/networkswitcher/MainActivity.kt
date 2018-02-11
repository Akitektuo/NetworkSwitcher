package com.akitektuo.networkswitcher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "Soon, if requested, this will open settings", Toast.LENGTH_LONG).show()
        finish()
    }
}
