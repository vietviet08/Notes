package com.vietquoc.todo_list.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vietquoc.todo_list.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE id =:id")
    fun getNoteById(id: Int): LiveData<Note>

    @Query("SELECT * FROM note WHERE noteTitle LIKE :query OR noteDesc LIKE :query ORDER BY id DESC")
    fun searchNote(query: String): LiveData<List<Note>>
}