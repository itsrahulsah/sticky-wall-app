package com.sample.stickywall.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.stickywall.R
import com.sample.stickywall.data.models.Notes
import com.sample.stickywall.databinding.NotesItemLayoutBinding

class NotesListAdapter(
                    private val onClick:(Notes?)->Unit,
                    private val onLongClick:(Notes)->Boolean):RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {
    private val notesList = mutableListOf<Notes?>()
    class ViewHolder(val binding:NotesItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = NotesItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notesList[position]
        if(note!=null) {

            with(holder) {
                binding.titleTv.text = note.title
                binding.notesTextView.text = note.note
                binding.addButton.visibility = View.GONE
                binding.root.setBackgroundResource(background(position))
                binding.root.setOnLongClickListener { onLongClick(note) }
                binding.root.setOnClickListener { onClick.invoke(note) }
            }
        }else{
            with(holder) {
                binding.addButton.visibility = View.VISIBLE
                binding.titleTv.visibility = View.GONE
                binding.notesTextView.visibility = View.GONE
                binding.root.setBackgroundResource(R.drawable.transprent)
                binding.addButton.setOnClickListener { onClick.invoke(null) }
            }
        }
    }

    private fun background(pos:Int):Int{
        return when{
            pos % 4 == 0  -> R.drawable.pitch_background
            pos % 3 == 0  -> R.drawable.blue_background
            pos % 2 == 0  -> R.drawable.yellow_background
            else  -> R.drawable.pink_background
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitNotes(list: List<Notes>){
        notesList.clear()
        notesList.addAll(list)
        notesList.add(null)
        notifyDataSetChanged()
    }

}