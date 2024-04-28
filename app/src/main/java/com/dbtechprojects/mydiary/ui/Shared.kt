package com.dbtechprojects.mydiary.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.dbtechprojects.mydiary.R

@Composable
fun GenericAppBar(
    title: String,
    onIconClick: (() -> Unit)? = null,
    onSettingsClick: (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    iconState: MutableState<Boolean>
) {
    TopAppBar(
        title = { Text(title) },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            // Conditionally display the main icon
            if (iconState.value && icon != null) {
                IconButton(
                    onClick = {
                        onIconClick?.invoke()
                    }
                ) {
                    icon()
                }
            }
            // Conditionally display the settings icon
            if (onSettingsClick != null) {
                IconButton(
                    onClick = {
                        onSettingsClick.invoke()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                }
            }
        }
    )
}
