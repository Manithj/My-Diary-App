package com.dbtechprojects.mydiary.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dbtechprojects.mydiary.model.Note

@Database(entities = [
  Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun NotesDao(): NotesDao

}