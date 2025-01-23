package com.vietquoc.todo_list.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vietquoc.todo_list.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDb : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

}