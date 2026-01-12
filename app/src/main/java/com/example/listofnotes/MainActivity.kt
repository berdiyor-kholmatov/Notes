@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.example.listofnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.listofnotes.data.db.MyDatabase
//import com.example.listofnotes.ui.noteList.ListDetailLayout
import com.example.listofnotes.ui.theme.ListOfNotesTheme

class MainActivity : ComponentActivity() {




    val db by lazy {
        Room.databaseBuilder(this, MyDatabase::class.java, "trustmebro")
            .fallbackToDestructiveMigration(dropAllTables = true)
            .allowMainThreadQueries()
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListOfNotesTheme {

                val users by db.getMyDao().getUsersFlow().collectAsState(emptyList())

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //ListDetailLayout(Modifier.padding(innerPadding), users, db)

                }
            }
        }
    }
}

