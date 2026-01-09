package com.example.listofnotes.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.listofnotes.MainActivity.NotesList
//
//@Composable
//fun NavigationDisplay(
//    backStack: MutableList<Any> = remember { mutableStateListOf(NewsList) }
//)
//{
//    NavDisplay(
//        backStack = backStack,
//        onBack = { backStack.removeLastOrNull() },
//        entryProvider = { key ->
//            when (key) {
//                is NotesList -> NavEntry(key) {
//                    val notesListViewModel = hiltViewModel<NewsListViewModel>()
//                    val state by notesListViewModel.state.collectAsState()
//                    NotesListView(state)
//                    {
//                        backStack.add(NewsDetail(it.slug))
//                    }
//                }
//
//                is NewsDetail -> NavEntry(key) {
//                    val newsDetailViewModel = hiltViewModel<NewsDetailViewModel>()
//                    val state by newsDetailViewModel.state.collectAsState()
//                    LaunchedEffect(key.slug) {
//                        newsDetailViewModel.getNews(key.slug)
//                    }
//                    NewsDetailScreen(state)
//                    {
//                        backStack.removeLastOrNull()
//                    }
//                }
//
//                else -> NavEntry(Unit) { Text("Unknown route") }
//            }
//        }
//    )
//
//}
