package com.example.listofnotes.ui.noteList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listofnotes.R
import com.example.listofnotes.data.db.MyDatabase
import com.example.listofnotes.data.db.NoteEntity
import com.example.listofnotes.domain.model.NoteTitle
import com.example.listofnotes.ui.theme.outlineDark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random



@Composable
fun NotesListView(state: ListViewState, onEvent: (ListEvent) -> Unit, onNoteClick: (NoteTitle) -> Unit, addButtonClicked: () -> Unit) {


    LazyColumn(
        modifier = Modifier.systemBarsPadding()
    ) {

        stickyHeader {
            Row(modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Notes", modifier = Modifier
                    .weight(1f),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 24.sp
                )
                IconButton(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(16.dp),
                    onClick = addButtonClicked
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_add_circle_24),
                        contentDescription = null,
                        tint =  MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }

            }
        }

        items(state.notes){ note ->
            Row(modifier = Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(16.dp),
                    onClick = addButtonClicked
                ) {
                    Icon(
                        painter = painterResource(id =
                            if ( note.isDone )
                                R.drawable.check_circle_24dp
                            else
                                R.drawable.circle_24dp),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
                TextButton({ onNoteClick(note) }, content = {Text(text = note.title, fontSize = 20.sp, color = MaterialTheme.colorScheme.onSecondaryContainer)}, modifier = Modifier
                    .weight(1f))


                IconButton(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(16.dp),
                    onClick = addButtonClicked
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete_24dp),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(24.dp)

                    )
                }

            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f))
            )
        }
    }
}

























@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailLayout(modifier: Modifier = Modifier, users: List<NoteEntity>, db: MyDatabase) {
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<Any>()
    val scope = rememberCoroutineScope()
    NavigableListDetailPaneScaffold(
        modifier = modifier,
        navigator = scaffoldNavigator,
        listPane = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                        .weight(1f),

                    ) {
                    items(items = users) {
                        Text(text = "Name: ${it.text}\n",
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .clickable {
                                    scope.launch {
                                        scaffoldNavigator.navigateTo(
                                            pane = ListDetailPaneScaffoldRole.Detail,
                                            contentKey = "ID: ${it.id}\n" +
                                                    "Content: ${it.text}\n" +
                                                    "Created at: ${it.dateOfCreating}\n"+
                                                    "Edited at: ${it.dateOfEditing}\n"
                                        )
                                    }
                                }
                                .padding(16.dp)
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            db.getMyDao().insert(
                                NoteEntity(
                                    text = "Name ${Random.nextInt()}",
                                    dateOfCreating = "${Random.nextInt()}",
                                    dateOfEditing = "${Random.nextInt()}",
                                    isDone = false,
                                    title = ""
                                )
                            )
                        }
                    }
                ) {
                    Text(text = "Add random name")
                }
            }

        },
        detailPane = {
            val content = scaffoldNavigator.currentDestination?.contentKey?.toString() ?: "Select an item"
            AnimatedPane {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ){
                    Text(content)
                    Row{
                        AssistChip(
                            onClick = {
                                scope.launch {
                                    scaffoldNavigator.navigateTo(
                                        pane = ListDetailPaneScaffoldRole.Extra,
                                        contentKey = "Option 1"
                                    )
                                }
                            },
                            label = {
                                Text("Option 1")
                            }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        AssistChip(
                            onClick = {
                                scope.launch {
                                    scaffoldNavigator.navigateTo(
                                        pane = ListDetailPaneScaffoldRole.Extra,
                                        contentKey = "Option 2"
                                    )
                                }
                            },
                            label = {
                                Text("Option 2")
                            }
                        )
                    }

                }
            }
        },
        extraPane = {
            val content = scaffoldNavigator.currentDestination?.contentKey?.toString() ?: "Select an item"
            AnimatedPane {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(content)
                }
            }
        }
    )
}