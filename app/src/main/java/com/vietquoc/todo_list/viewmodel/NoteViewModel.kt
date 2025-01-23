package com.vietquoc.todo_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vietquoc.todo_list.model.Note
import com.vietquoc.todo_list.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    fun addNote(note: Note) = viewModelScope.launch {
        noteRepository.insert(note = note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.update(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.delete(note)
    }

    fun getAllNotes() = noteRepository.getAllNotes()

    fun searchNote(query: String) = noteRepository.searchNote(query)

}