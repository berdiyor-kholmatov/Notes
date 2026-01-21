package com.example.listofnotes.ui.noteDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listofnotes.R

@Composable
fun NoteDetailView(
    state: DetailViewState,
    onEvent: (DetailEvent) -> Unit,
    onEditButtonClicked: () -> Unit,
)
{
    LazyColumn(
        modifier = Modifier.fillMaxSize().systemBarsPadding()
            .background(MaterialTheme.colorScheme.secondaryContainer),
    ){
        item{
            Row(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                IconButton(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(8.dp),
                    onClick = onEditButtonClicked
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_arrow_back_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(text = state.title, modifier = Modifier
                    .weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                IconButton(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(8.dp),
                    onClick = onEditButtonClicked
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_square_24dp),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Column ( modifier = Modifier.fillMaxSize()
                .padding(16.dp)
            ) {
                Text(text = state.text, modifier = Modifier.weight(1f))
                Text(text = "Date of creating: ${state.dateOfCreating}", modifier = Modifier.weight(1f))
                Text(text = "Date od last editing: ${state.dateOfEditing}", modifier = Modifier.weight(1f))
            }

        }
    }

}