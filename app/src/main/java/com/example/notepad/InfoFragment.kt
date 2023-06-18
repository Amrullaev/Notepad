package com.example.notepad

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.notepad.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {

    private lateinit var _binding: FragmentInfoBinding
    private val mBinding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        _binding = FragmentInfoBinding.inflate(layoutInflater, container, false)
        return mBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.infoCv2.setOnClickListener { open("https://t.me/amrullaev_portfolio") }
        mBinding.infoCv6.setOnClickListener { open("https://www.instagram.com/uchqun_amrullaev/") }
    }

    private fun open(uri: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
        }
    }


}