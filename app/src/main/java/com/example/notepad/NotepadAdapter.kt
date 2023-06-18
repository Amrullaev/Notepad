package com.example.notepad

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.databinding.ItemNoteBinding

class NotepadAdapter(
    private val NotepadHelper: HelperNotepad
) : RecyclerView.Adapter<VH>() {

    private var noteList: ArrayList<Note>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding, NotepadHelper)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (!noteList.isNullOrEmpty())
            holder.bind(noteList!![position])
    }

    override fun getItemCount(): Int {
        return if (noteList.isNullOrEmpty()) 0 else noteList!!.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNotepad(list: List<Note?>) {
        noteList = ArrayList()
        list.forEach { if (it != null) noteList!!.add(it) }
        notifyDataSetChanged()
    }


}

class VH(private val mBinding: ItemNoteBinding, private val notepadHelper: HelperNotepad) :
    RecyclerView.ViewHolder(mBinding.root) {
    fun bind(item: Note) {
        mBinding.root.setOnLongClickListener {
            notepadHelper.onItemLongClick(item)
            return@setOnLongClickListener true
        }
        mBinding.root.setOnClickListener {
            expand()
        }
        mBinding.tvTitle.text = item.title
        mBinding.tvText.text = item.text
        mBinding.itemCvChange.setOnClickListener { notepadHelper.onItemClicked(item) }

    }

    private fun expand() {
        if (mBinding.tvText.isVisible) {
            mBinding.tvText.isVisible = false
            mBinding.itemCvChange.isVisible = false
            mBinding.anim.rotation = 0.0F
        } else {
            mBinding.tvText.isVisible = true
            mBinding.itemCvChange.isVisible = true
            mBinding.anim.rotation = 180F
        }
    }

}