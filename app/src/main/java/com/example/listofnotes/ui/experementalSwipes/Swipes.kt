package com.example.listofnotes.ui.experementalSwipes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.SwipeToDismissBoxValue.Settled
import androidx.compose.material3.SwipeToDismissBoxValue.StartToEnd
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



data class TodoItem(
    val itemDescription: String,
    var isItemDone: Boolean = false
)


@Composable
fun SwipeItemExample() {
    val todoItems = remember {
        mutableStateListOf(
            TodoItem("Pay bills"), TodoItem("Buy groceries"),
            TodoItem("Go to gym"), TodoItem("Get dinner")
        )
    }

    LazyColumn(
        modifier = Modifier.systemBarsPadding().fillMaxSize(),
    ) {
        items(
            items = todoItems,
            key = { it.itemDescription }
        ) { todoItem ->
            TodoListItem(
                todoItem = todoItem,
                onToggleDone = { todoItem ->
                    todoItem.isItemDone = !todoItem.isItemDone
                },
                onRemove = { todoItem ->
                    todoItems -= todoItem
                },
                modifier = Modifier.animateItem()
            )
        }
    }
}


@Composable
fun TodoListItem(
    todoItem: TodoItem,
    onToggleDone: (TodoItem) -> Unit,
    onRemove: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == StartToEnd) onToggleDone(todoItem)
            else if (it == EndToStart) onRemove(todoItem)
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
                        if (todoItem.isItemDone) Icons.Default.Done else Icons.Default.Clear,
                        contentDescription = if (todoItem.isItemDone) "Done" else "Not done",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Blue)
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
                            .background(Color.Red)
                            .wrapContentSize(Alignment.CenterEnd)
                            .padding(12.dp),
                        tint = Color.White
                    )
                }
                Settled -> {}
            }
        }
    ) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)) {
            Text(todoItem.itemDescription)
            Text("swipe me to update or remove.")
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f))
            )
        }
    }
}
