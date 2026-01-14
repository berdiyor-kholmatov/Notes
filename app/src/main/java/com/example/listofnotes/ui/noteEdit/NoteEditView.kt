package com.example.listofnotes.ui.noteEdit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@Composable
fun NoteEditView(
    state: NoteEditState,
    onEvent: (NoteEditEvent) -> Unit,
    onBack: () -> Unit = {},
) {


    Column( modifier = Modifier.systemBarsPadding()){
        TextField(
            value = state.title,
            onValueChange = {
                onEvent(NoteEditEvent.TitleChanged(it))
            },
            placeholder = {
                Text(text = "Title")
            },
        )

        TextField(
            value = state.text,
            onValueChange = {
                onEvent(NoteEditEvent.TextChanged(it))
            },
            placeholder = {
                Text(text  = "Enter the text")
            },
        )

        LaunchedEffect(key1 = state.isSaved){
            if (state.isSaved){
                onEvent(NoteEditEvent.BackActionWillBeApplied)
                onBack()
            }
        }

        Button(
            onClick = {
                onEvent(NoteEditEvent.SaveButtonClicked)
            } ,
            enabled = state.isButtonEnabled
        ) {
            Text(text = "Save")
        }


    }


}