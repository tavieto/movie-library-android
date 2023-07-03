package dev.tavieto.movielibrary.core.uikit.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun SearchTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
) {
    BaseTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = label,
        keyboardOptions = keyboardOptions.copy(
            keyboardType = KeyboardType.Text,
            autoCorrect = true,
            capitalization = KeyboardCapitalization.Sentences
        ),
        keyboardActions = keyboardActions,
        isError = isError,
        errorMessage = errorMessage,
        trailingIcon = {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
        }
    )
}
