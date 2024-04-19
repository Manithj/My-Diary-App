package com.dbtechprojects.mydiary
import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.room.Room
import com.dbtechprojects.mydiary.persistence.NotesDao
import com.dbtechprojects.mydiary.persistence.NotesDatabase

class MyDiaryApp : Application(){

    private var db : NotesDatabase? = null


    init {
        instance = this
    }

    private fun getDb(): NotesDatabase {
        return if (db != null){
            db!!
        } else {
            db = Room.databaseBuilder(
                instance!!.applicationContext,
                NotesDatabase::class.java, Constants.DATABASE_NAME
            ).fallbackToDestructiveMigration()// remove in prod
                .build()
            db!!
        }
    }


    companion object {
        private var instance: MyDiaryApp? = null

        fun getDao(): NotesDao {
            return instance!!.getDb().NotesDao()
        }

        fun getUriPermission(uri: Uri){
            instance!!.applicationContext.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }

    }


}