package com.example.notepad.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notepad.InfoFragment
import com.example.notepad.R
import com.example.notepad.databinding.FragmentFirstBinding
import com.example.notepad.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private lateinit var _binding: FragmentSettingsBinding
    private val mBinding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)

        mBinding.info.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, InfoFragment(), InfoFragment::javaClass.name)
                .addToBackStack(InfoFragment::javaClass.name).commit()
        }

        return mBinding.root
    }


}