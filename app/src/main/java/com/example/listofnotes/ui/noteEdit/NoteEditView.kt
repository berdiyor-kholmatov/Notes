package com.example.listofnotes.ui.noteEdit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteEditView(
    state: NoteEditState,
    onEvent: (NoteEditEvent) -> Unit,
    onBack: () -> Unit = {},
) {


    Column(modifier = Modifier.systemBarsPadding()) {
        TextField(
            value = state.title,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 16.dp),
            onValueChange = {
                onEvent(NoteEditEvent.TitleChanged(it))
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
                onEvent(NoteEditEvent.TextChanged(it))
            },
            placeholder = {
                Text(text = "Enter the text")
            },
        )

        if (state.isSaved.getContentIfNotHandled() == true) {
            onBack()
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onEvent(NoteEditEvent.SaveButtonClicked)
            },
            enabled = state.isButtonEnabled
        ) {
            Text(text = "Save")
        }


    }


}