package com.jonathan.mycinemakmp

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import data.local.database.DbConfigure
import data.local.database.MyCinemaDatabase


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = DbConfigure(applicationContext).createDatabase()

        setContent {
            App(database)
        }
    }
}

