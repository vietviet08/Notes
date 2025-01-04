package com.vietquoc.todo_list.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.vietquoc.todo_list.MainActivity
import com.vietquoc.todo_list.R
import com.vietquoc.todo_list.databinding.FragmentEditNoteBinding
import com.vietquoc.todo_list.model.Note
import com.vietquoc.todo_list.viewmodel.NoteViewModel

class EditNoteFragment : Fragment(R.layout.fragment_edit_note), MenuProvider {

    private var editNoteBinding: FragmentEditNoteBinding? = null
    private val binding get() = editNoteBinding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var currentNote: Note
    private lateinit var editNoteView: View

    private val args: EditNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editNoteBinding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        noteViewModel = (activity as MainActivity).noteViewModel
        editNoteView = view
        currentNote = args.note!!

        binding.editNoteTitle.setText(currentNote.noteTitle)
        binding.editNoteDesc.setText(currentNote.noteDesc)

        binding.editNoteFab.setOnClickListener {
            updateNote(editNoteView)
        }

    }

    private fun updateNote(view: View) {
        val noteTitle = binding.editNoteTitle.text.toString().trim()
        val noteDesc = binding.editNoteDesc.text.toString().trim()

        if (noteTitle.isNotEmpty()) {
            val note = Note(currentNote.id, noteTitle, noteDesc)
            noteViewModel.updateNote(note)
            Toast.makeText(editNoteView.context, "Note updated successfully", Toast.LENGTH_SHORT)
                .show()
            view.findNavController()
                .popBackStack(R.id.homeFragment, false)
        } else {
            Toast.makeText(editNoteView.context, "Please enter note title", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editNoteBinding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.deleteMenu -> {
                deleteNote(editNoteView)
                return true
            }

            else -> return false
        }
    }

    private fun deleteNote(view: View) {
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Are you sure you want to delete this note?")
            setPositiveButton("Delete") { _, _ ->
                noteViewModel.deleteNote(currentNote)

                Toast.makeText(
                    editNoteView.context,
                    "Note deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()

                view.findNavController()
                    .popBackStack(R.id.homeFragment, false)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

}