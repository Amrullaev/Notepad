package com.example.notepad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.room.Delete
import com.example.notepad.databinding.FragmentSecondBinding


class SecondFragment(val note: Note?) : Fragment() {
    private lateinit var _binding: FragmentSecondBinding
    private val mBinding get() = _binding

    private lateinit var viewModel: NoteViewMoel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (note != null) {
            mBinding.etText.setText(note.text)
            mBinding.etTitle.setText(note.title)
        }

        viewModel =
            ViewModelProvider(requireActivity())[NoteViewMoel(requireActivity().application)::class.java]
        mBinding.etTitle.text.toString()

        mBinding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        mBinding.ivClearTitle.setOnClickListener {
            mBinding.etTitle.setText("")

        }

        mBinding.ivClearText.setOnClickListener {
            mBinding.etText.text.clear()
        }

        mBinding.ivDelete1.setOnClickListener {
            viewModel.deleteNote(note)
            requireActivity().onBackPressed()
        }
        mBinding.ivSave.setOnClickListener {
            if (note == null)
                viewModel.saveNoteToDB(
                    Note(
                        null,
                        mBinding.etTitle.text.toString(),
                        mBinding.etText.text.toString()
                    )
                )
            else {
                note.text = mBinding.etText.text.toString()
                note.title = mBinding.etTitle.text.toString()
                viewModel.saveNoteToDB(note)
            }
            requireActivity().onBackPressed()
        }
    }

}