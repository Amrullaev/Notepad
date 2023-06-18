package com.example.notepad.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.example.notepad.InfoFragment
import com.example.notepad.R
import com.example.notepad.databinding.FragmentFirstBinding
import com.example.notepad.databinding.FragmentSettingsBinding
import com.example.notepad.utils.SharedPref
import com.example.notepad.utils.YourPreference


class SettingsFragment : Fragment() {
    private lateinit var _binding: FragmentSettingsBinding
    private val mBinding get() = _binding
    lateinit var yourPreference: YourPreference
    private lateinit var sPref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        yourPreference = YourPreference.getInstance(mBinding.root.context)
        mBinding.info.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, InfoFragment(), InfoFragment::javaClass.name)
                .addToBackStack(InfoFragment::javaClass.name).commit()
        }


        sPref = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        if (sPref.getString("mode", "")=="night"){
            mBinding.themeSwitch.isChecked = true
            mBinding.modeTv.text = "Tungi rejim"
        }
        mBinding.themeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed) {
                if (isChecked) {
                    sPref.edit().putString("mode", "night").apply()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    mBinding.modeTv.text = "Tungi rejim"
                } else {
                    sPref.edit().putString("mode", "day").apply()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    mBinding.modeTv.text = "Kunduzgi rejim"
                }
            }
        }
        return mBinding.root
    }


}