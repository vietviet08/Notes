package com.vietquoc.todo_list.repository

import com.vietquoc.todo_list.db.NoteDb
import com.vietquoc.todo_list.model.Note

class NoteRepository(private val db: NoteDb) {

    suspend fun insert(note: Note) = db.getNoteDao().insert(note)
    suspend fun update(note: Note) = db.getNoteDao().update(note)
    suspend fun delete(note: Note) = db.getNoteDao().delete(note)

    fun getAllNotes() = db.getNoteDao().getAllNotes()
    fun searchNote(query: String) = db.getNoteDao().searchNote(query)

}