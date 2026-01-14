package com.example.listofnotes.ui.noteAdd

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@Composable
fun NoteAddView(
    state: NoteAddState,
    onEvent: (NoteAddEvent) -> Unit,
    onBack: () -> Unit = {},
) {
    Column( modifier = Modifier.systemBarsPadding()){
        TextField(
            value = state.title,
            onValueChange = {
                onEvent(NoteAddEvent.TitleChanged(it))
            },
            placeholder = {
                Text(text = "Title")
            },
        )

        TextField(
            value = state.text,
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

        Button(
            onClick = {
                onEvent(NoteAddEvent.SaveButtonClicked)
            } ,
            enabled = state.isButtonEnabled
        ) {
            Text(text = "Save")
        }


    }


}