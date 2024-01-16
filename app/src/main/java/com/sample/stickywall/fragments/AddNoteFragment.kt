package com.sample.stickywall.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sample.stickywall.R
import com.sample.stickywall.data.AppDatabase
import com.sample.stickywall.data.models.Notes
import com.sample.stickywall.databinding.FragmentAddNoteBinding
import com.sample.stickywall.fragments.viewModel.AddNoteViewModel
import com.sample.stickywall.fragments.viewModel.ViewModelFactory
import com.sample.stickywall.repository.NotesRepository


class AddNoteFragment : Fragment(R.layout.fragment_add_note) {
    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var viewModel: AddNoteViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAddNoteBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        val notesRepo = NotesRepository(AppDatabase.getDatabase(requireContext()))
        viewModel = ViewModelProvider(this,ViewModelFactory(notesRepo))[AddNoteViewModel::class.java]
        init()
    }

    private fun init() {
        binding.editTextTitle.setText(arguments?.getString("title","") ?: "")
        binding.editTextNotes.setText(arguments?.getString("note","") ?: "")
        binding.editTextNotes.requestFocus()
        binding.buttonSave.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val description = binding.editTextNotes.text.toString()
            if(title.isBlank() || description.isBlank()){
                Snackbar.make(binding.root,"Please fill details.....",Snackbar.LENGTH_SHORT).show()
            }else{
                viewModel.insertNote(
                    Notes(
                        title = title,
                        note = description
                    )
                )
                findNavController().popBackStack()
            }

        }
    }

}