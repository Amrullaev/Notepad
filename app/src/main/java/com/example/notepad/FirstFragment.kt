package com.example.notepad

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notepad.databinding.FragmentFirstBinding

class FirstFragment : Fragment(), HelperNotepad {
    private lateinit var _binding: FragmentFirstBinding
    private val mBinding get() = _binding
    private lateinit var appDataBase: NoteDataBase
    private lateinit var adapter: NotepadAdapter
    private lateinit var viewModel: NoteViewMoel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return mBinding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[NoteViewMoel::class.java]
        adapter = NotepadAdapter(this)
        mBinding.rv.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rv.adapter = adapter
        viewModel.getNotesFromDB()
        mBinding.cv2.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, SecondFragment(null), SecondFragment::javaClass.name)
                .addToBackStack(SecondFragment::javaClass.name).commit()
        }
        mBinding.cv1.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, InfoFragment(), InfoFragment::javaClass.name)
                .addToBackStack(InfoFragment::javaClass.name).commit()
        }

        viewModel =
            ViewModelProvider(requireActivity())[NoteViewMoel(requireActivity().application)::class.java]
        viewModel.mNoteList.observe(viewLifecycleOwner) {
            if (it != null)
                adapter.setNotepad(it)
            else {
                Log.d("XXXXX", "onViewCreated:$it ")
            }
        }
    }

    private fun addFragment(fragment: SecondFragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setTransition(TRANSIT_FRAGMENT_FADE)
            .replace(R.id.main_container, fragment)
            .addToBackStack(SecondFragment::javaClass.name).commit()
    }


    override fun onItemClicked(note: Note) {
        addFragment(SecondFragment(note))
    }

    override fun onItemLongClick(note: Note) {
        val noteDialog = AlertDialog.Builder(requireContext())
        noteDialog.setTitle("Haqiqatdan ham ushbu yozuvlarni o'chirib tashlamoqchimisiz?")
        val noteDialogItem = arrayOf("OK", "Bekor qilish")
        noteDialog.setItems(noteDialogItem) { dialog, which ->
            when (which) {
                0 -> {
                    viewModel.deleteNote(note)
                    viewModel.getNotesFromDB()
                }
                1 -> dialog.dismiss()
            }
        }
            .show()
    }


}
