package com.example.listofnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.listofnotes.ui.experementalSwipes.SwipeItemExample
import com.example.listofnotes.ui.navigation.NavigationDisplay
import com.example.listofnotes.ui.theme.ListOfNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListOfNotesTheme {
                NavigationDisplay()
//                SwipeItemExample()
            }
        }
    }
}

