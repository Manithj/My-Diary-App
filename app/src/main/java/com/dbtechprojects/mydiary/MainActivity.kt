package com.dbtechprojects.mydiary
import androidx.compose.material.Button
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dbtechprojects.mydiary.ui.EditNote.NoteEditScreen
import com.dbtechprojects.mydiary.ui.NoteDetail.NoteDetailScreen
import com.dbtechprojects.mydiary.ui.NotesList.NotesList
import com.dbtechprojects.mydiary.ui.NotesViewModel
import com.dbtechprojects.mydiary.ui.NotesViewModelFactory
import com.dbtechprojects.mydiary.ui.createNote.CreateNoteScreen

class MainActivity : ComponentActivity() {

    private lateinit var notesViewModel : NotesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // retrieve viewModel
        notesViewModel =  NotesViewModelFactory(MyDiaryApp.getDao()).create(NotesViewModel::class.java)


        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Constants.NAVIGATION_NOTES_LIST
            ) {
                // Notes List
                composable(Constants.NAVIGATION_NOTES_LIST) { NotesList(navController, notesViewModel) }

                // Notes Detail page
                composable(
                    Constants.NAVIGATION_NOTE_DETAIL,
                    arguments = listOf(navArgument(Constants.NAVIGATION_NOTE_ID_Argument) {
                        type = NavType.IntType
                    })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getInt(Constants.NAVIGATION_NOTE_ID_Argument)
                        ?.let { NoteDetailScreen(noteId = it, navController, notesViewModel) }
                }

                // Notes Edit page
                composable(
                    Constants.NAVIGATION_NOTE_EDIT,
                    arguments = listOf(navArgument(Constants.NAVIGATION_NOTE_ID_Argument) {
                        type = NavType.IntType
                    })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getInt(Constants.NAVIGATION_NOTE_ID_Argument)
                        ?.let { NoteEditScreen(noteId = it, navController, notesViewModel) }
                }

                // Create Note Page
                composable(Constants.NAVIGATION_NOTES_CREATE) { CreateNoteScreen(navController, notesViewModel) }

                composable(route = Constants.NAVIGATION_SETTINGS) {
                    SettingsScreen(context = this@MainActivity.applicationContext)
                }

            }

        }
    }
}

//@Composable
//fun SettingsScreen() {
//    Surface(modifier = Modifier.fillMaxSize()) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text("Settings", style = MaterialTheme.typography.h5)
//            Spacer(modifier = Modifier.height(20.dp))
//            Text("App Creator: Manith Jayaba")
//            Text("Index No: D/BCE/22/0006")
//            // Add more settings options as needed
//        }
//    }
//}



@Composable
fun SettingsScreen(context: Context) {
    val (title, setTitle) = remember { mutableStateOf("") }
    val (updatedStr, setUpdatedStr) = remember {
        mutableStateOf(context.resources.getString(R.string.name))
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Settings", style = MaterialTheme.typography.h5)
            TextField(
                value = title,
                onValueChange = setTitle,
                label = { Text("Enter New Name") }
            )
            Button(
                onClick = {
                    setUpdatedStr(title)
                }
            ) {
                Text("Update String")
            }
            Text(title)
            Text(updatedStr)
            Text("App Creator: Manith Jayaba")
            Text("Index No: D/BCE/22/0006")
            // Add more settings options as needed
        }
    }
}