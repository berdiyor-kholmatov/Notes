package com.example.listofnotes.ui.noteEdit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.listofnotes.R

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
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(bottom = 16.dp),
            onValueChange = {
                onEvent(NoteEditEvent.TitleChanged(it))
            },
            placeholder = {
                Text(text = stringResource(R.string.title_placeholder))
            },
        )

        TextField(
            value = state.text,
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(bottom = 16.dp)
                .weight(1f),
            onValueChange = {
                onEvent(NoteEditEvent.TextChanged(it))
            },
            placeholder = {
                Text(text = stringResource(R.string.content_placeholder))
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
            Text(text = stringResource(R.string.save_button))
        }


    }


}