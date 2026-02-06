package com.example.listofnotes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.listofnotes.ui.noteAdd.NoteAddView
import com.example.listofnotes.ui.noteAdd.NoteAddViewModel
import com.example.listofnotes.ui.noteDetail.DetailViewModel
import com.example.listofnotes.ui.noteDetail.NoteDetailView
import com.example.listofnotes.ui.noteEdit.NoteEditView
import com.example.listofnotes.ui.noteEdit.NoteEditViewModel
import com.example.listofnotes.ui.noteList.ListViewModel
import com.example.listofnotes.ui.noteList.NotesListView
import com.example.listofnotes.ui.scenes.ListDetailScene
import com.example.listofnotes.ui.scenes.rememberListDetailSceneStrategy


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
        sceneStrategy = rememberListDetailSceneStrategy(),
        entryProvider = { key ->
            when (key) {
                is NotesList -> NavEntry(
                    key = key,
                    metadata = ListDetailScene.listPane()
                ) {
                    val notesListViewModel: ListViewModel = hiltViewModel()
                    val state by notesListViewModel.state.collectAsState()
                    NotesListView(state,  notesListViewModel::onEvent, onNoteClick = {
                        backStack.add(NoteDetail(it.id))
                    }, addButtonClicked = {
                        backStack.add(AddOrEditNote(-1))
                    })
                }

                is NoteDetail -> NavEntry(
                    key = key,
                    metadata = ListDetailScene.detailPane()
                ) {
                    val id = key.id // bu note id
                    val noteDetailViewModel: DetailViewModel = hiltViewModel()
                    noteDetailViewModel.setNoteId(id)       //am i needed to use flow<note?>, i think its not needed, as i call function that takes the actual information from db
                    val state by noteDetailViewModel.state.collectAsState()
                    NoteDetailView(
                        state = state,
                        onEvent = noteDetailViewModel::onEvent,
                        onBack = {
                            backStack.removeLastOrNull()
                        },
                        onEditButtonClicked = {
                            backStack.add(AddOrEditNote(id))
                        }
                    )
                }

                is AddOrEditNote -> NavEntry(key) {
                    if (key.id == -1) { // bu note id yoki -1 agar  -1 bo'lsa yangi note yaratish kk
                        val noteAddViewModel: NoteAddViewModel = hiltViewModel()
                        val state by noteAddViewModel.state.collectAsState()
                        NoteAddView(state, noteAddViewModel::onEvent, onBack = {
                            backStack.removeLastOrNull()
                        })
                    } else { // bu edit qilish kk
                        val noteEditViewModel: NoteEditViewModel =
                            hiltViewModel<NoteEditViewModel, NoteEditViewModel.Factory>(
                                creationCallback = { factory ->
                                    factory.create(noteId = key.id)
                                },
                                key = key.id.toString()
                            )
                        val state by noteEditViewModel.state.collectAsState()
                        NoteEditView(state, noteEditViewModel::onEvent, onBack = {
                            backStack.removeLastOrNull()
                        })
                    }
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
