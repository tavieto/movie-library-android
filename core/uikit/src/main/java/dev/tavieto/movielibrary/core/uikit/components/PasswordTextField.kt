package dev.tavieto.movielibrary.core.uikit.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import dev.tavieto.movielibrary.core.uikit.R

@Composable
fun PasswordTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    isError: Boolean = false,
    isPasswordVisible: Boolean = false,
    onPasswordVisibilityChange: (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    BaseTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        visualTransformation = remember(isPasswordVisible) {
            if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        },
        label = label,
        keyboardOptions = keyboardOptions.copy(
            keyboardType = KeyboardType.Password,
            autoCorrect = false
        ),
        keyboardActions = keyboardActions,
        isError = isError,
        errorMessage = errorMessage,
        trailingIcon = {
            Box {
                IconButton(onClick = { onPasswordVisibilityChange?.invoke() }) {
                    AnimatedVisibility(visible = isPasswordVisible) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_hide),
                            contentDescription = null
                        )
                    }
                    AnimatedVisibility(visible = isPasswordVisible.not()) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_show),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    )
}
