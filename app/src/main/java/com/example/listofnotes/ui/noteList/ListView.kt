package com.example.listofnotes.ui.noteList

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listofnotes.R
import com.example.listofnotes.domain.model.NoteTitle
import androidx.compose.material3.SwipeToDismissBoxValue.StartToEnd
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.SwipeToDismissBoxValue.Settled
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.stringResource
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow


@Composable
fun NotesListView(state: ListViewState, pagingFlow: Flow<PagingData<NoteTitle>>, onEvent: (ListEvent) -> Unit, onNoteClick: (NoteTitle) -> Unit, addButtonClicked: () -> Unit) {

    val notes = pagingFlow.collectAsLazyPagingItems()


    Row(
        modifier = Modifier.systemBarsPadding().fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
        ) {

            stickyHeader {
                Row(modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        modifier = Modifier
                            .size(56.dp)
                            .padding(16.dp),
                        onClick = {  }
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.menu_24),
                            contentDescription = null,
                            tint =  MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = stringResource(R.string.notes), modifier = Modifier,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 24.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))

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

            items(
                count = notes.itemCount,
                key = { notes[it]!!.id },
                contentType = { notes[it]!!::class }
            ){index: Int ->
                val note = notes[index]!!   //Im not sure about it
                NoteListItem(
                    noteItem = note,
                    onNoteItemClick = onNoteClick,
                    onToggleDone = { onEvent(ListEvent.IsDoneButtonClicked(it.id)) },
                    onRemove = { onEvent(ListEvent.DeleteButtonClicked(it.id)) },
                    modifier = Modifier.animateItem()
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(horizontal = 6.dp)
                        .background(MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f))
                )

            }



//            items(
//                items = state.notes,
//                key = { it.id }
//            ) { note ->
//                NoteListItem(
//                    noteItem = note,
//                    onNoteItemClick = onNoteClick,
//                    onToggleDone = { onEvent(ListEvent.IsDoneButtonClicked(it.id)) },
//                    onRemove = { onEvent(ListEvent.DeleteButtonClicked(it.id)) },
//                    modifier = Modifier.animateItem()
//                )
//                Spacer(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(1.dp)
//                        .padding(horizontal = 6.dp)
//                        .background(MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f))
//                )
//            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                //.weight(2f)
                .width(1.dp)
                .background(MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f))
        )
    }
}



@Composable
fun NoteListItem(
    noteItem: NoteTitle,
    onNoteItemClick: (NoteTitle) -> Unit,
    onToggleDone: (NoteTitle) -> Unit,
    onRemove: (NoteTitle) -> Unit,
    modifier: Modifier = Modifier,
) {
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == StartToEnd) onToggleDone(noteItem)
            else if (it == EndToStart) onRemove(noteItem)
            // Reset item when toggling done status
            it != StartToEnd
        }
    )

    SwipeToDismissBox(
        state = swipeToDismissBoxState,
        modifier = modifier.fillMaxSize(),
        backgroundContent = {
            when (swipeToDismissBoxState.dismissDirection) {
                StartToEnd -> {
                    Icon(
                        if (noteItem.isDone) Icons.Default.Done else Icons.Default.Clear,
                        contentDescription = if (noteItem.isDone) "Done" else "Not done",
                        modifier = Modifier
                            .fillMaxSize()
//                            .drawBehind {
//                                drawRect(lerp(Color.LightGray, Color.Blue, swipeToDismissBoxState.progress))
//                            }
                            .background(lerp(
                                Color.LightGray,
                                Color.Blue,
                                swipeToDismissBoxState.progress
                            ))
                            .wrapContentSize(Alignment.CenterStart)
                            .padding(12.dp),
                        tint = Color.White
                    )
                }
                EndToStart -> {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove item",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(lerp(
                                Color.LightGray,
                                Color.Red,
                                swipeToDismissBoxState.progress
                            ))
                            .wrapContentSize(Alignment.CenterEnd)
                            .padding(12.dp),
                        tint = Color.White
                    )
                }
                Settled -> {}
            }
        }
    ) {
        TextButton(
            onClick = { onNoteItemClick(noteItem) },
            content = {Text(
                text = noteItem.title,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )},
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer))
    }
}
