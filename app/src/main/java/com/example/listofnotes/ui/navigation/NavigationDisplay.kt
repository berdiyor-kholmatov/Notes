package com.example.listofnotes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.listofnotes.ui.noteDetail.DetailViewModel
import com.example.listofnotes.ui.noteDetail.NoteDetailView
import com.example.listofnotes.ui.noteList.ListViewModel
import com.example.listofnotes.ui.noteList.NotesListView


data object NotesList
data class AddOrEditNote(val id: Int)
data class NoteDetail(val id: Int)


@Composable
fun NavigationDisplay(
    backStack: MutableList<Any> = remember { mutableStateListOf(NotesList) }
) {
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is NotesList -> NavEntry(key) {
                    val notesListViewModel: ListViewModel = hiltViewModel()
                    val state by notesListViewModel.state.collectAsState()
                    NotesListView(state, notesListViewModel::onEvent, onNoteClick = {
                        backStack.add(NoteDetail(it.id))
                    }, addButtonClicked = {
                        backStack.add(AddOrEditNote(-1))
                    })


                }

                is NoteDetail -> NavEntry(key) {
                    val id = key.id // bu note id
                    val noteDetailViewModel: DetailViewModel = hiltViewModel()
                    noteDetailViewModel.setNoteId(id)
                    val state by noteDetailViewModel.state.collectAsState()
                    NoteDetailView(
                        state = state,
                        onEvent = noteDetailViewModel::onEvent,
                        onEditButtonClicked = {
                            backStack.add(AddOrEditNote(id))
                        }
                    )
                }

                is AddOrEditNote -> NavEntry(key) {
                    key.id // bu note id yoki -1 agar  -1 bo'lsa yangi note yaratish kk


                }

                else -> NavEntry(Unit) {

                }
            }

        }


    )
}


//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.remember
//import androidx.navigation3.runtime.NavEntry
//import androidx.navigation3.ui.NavDisplay
//import com.example.listofnotes.MainActivity.NotesList
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
