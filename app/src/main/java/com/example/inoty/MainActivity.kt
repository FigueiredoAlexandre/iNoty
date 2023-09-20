package com.example.inoty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.navigation.featurenavigations.navigationrouter.INotyRouter
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val router: INotyRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router.toDiaryList(this)
    }
}