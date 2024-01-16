package com.sample.stickywall.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sample.stickywall.R
import com.sample.stickywall.adapters.NotesListAdapter
import com.sample.stickywall.data.AppDatabase
import com.sample.stickywall.data.models.Notes
import com.sample.stickywall.databinding.FragmentHomeBinding
import com.sample.stickywall.fragments.viewModel.HomeViewModel
import com.sample.stickywall.fragments.viewModel.ViewModelFactory
import com.sample.stickywall.repository.NotesRepository


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter:NotesListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        val notesRepo = NotesRepository(AppDatabase.getDatabase(requireContext()))
        viewModel = ViewModelProvider(this,ViewModelFactory(notesRepo))[HomeViewModel::class.java]
        adapter = NotesListAdapter({note ->
                if(note != null){
                    val bundle = Bundle().also {
                        it.putString("title",note.title)
                        it.putString("note",note.note)
                    }
                    findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment,bundle)
                }else{
                    findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment,null)
                }
        }, {
            deleteNote(it)
                true
        })
        init()
    }

    private fun init(){
        binding.notesList.layoutManager = GridLayoutManager(requireContext(),3)
        binding.notesList.adapter = adapter
        viewModel.notes.observe(viewLifecycleOwner){
            adapter.submitNotes(it)
        }
    }


    private fun deleteNote(note:Notes){
       AlertDialog.Builder(requireContext())
            .setTitle("Are you sure?")
            .setMessage("Do you want delete")
            .setPositiveButton("yes") { _, _ ->
                viewModel.delete(note)
            }
            .setNegativeButton("no"){dialog,_-> dialog.dismiss()}
            .show()
    }

}