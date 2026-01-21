package com.example.listofnotes.ui.noteAdd

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteAddView(
    state: NoteAddState,
    onEvent: (NoteAddEvent) -> Unit,
    onBack: () -> Unit = {},
) {
    Column( modifier = Modifier.systemBarsPadding()
        .padding(16.dp)
    ){
        TextField(
            value = state.title,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 16.dp),
            onValueChange = {
                onEvent(NoteAddEvent.TitleChanged(it))
            },
            placeholder = {
                Text(text = "Title")
            },
        )

        TextField(
            value = state.text,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 16.dp)
                .weight(1f),
            onValueChange = {
                onEvent(NoteAddEvent.TextChanged(it))
            },
            placeholder = {
                Text(text  = "Enter the text")
            },
        )

        if (state.isSaved.getContentIfNotHandled() == true) {
            onBack()
        }

        //Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                onEvent(NoteAddEvent.SaveButtonClicked)
            } ,
            modifier = Modifier.fillMaxWidth(),

            enabled = state.isButtonEnabled
        ) {
            Text(text = "Save")
        }


    }


}