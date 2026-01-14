package com.example.listofnotes.ui.noteDetail

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
        modifier = Modifier.systemBarsPadding().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        item{
            Row(){
                Spacer(modifier = Modifier.weight(1f))
                Text(text = state.title, modifier = Modifier
                    .weight(1f),
                    fontSize = 24.sp
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
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }

            }

            Text(text = state.text)
            Text(text = state.dateOfCreating)
            Text(text = state.dateOfEditing)
        }
    }

}