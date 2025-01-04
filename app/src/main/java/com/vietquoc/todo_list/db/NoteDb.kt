package com.vietquoc.todo_list.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vietquoc.todo_list.model.Note
import kotlin.concurrent.Volatile

@Database(entities = [Note::class], version = 1)
abstract class NoteDb : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        @Volatile
        private var instance: NoteDb? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                NoteDb::class.java,
                "note_db"
            ).build()
    }
}