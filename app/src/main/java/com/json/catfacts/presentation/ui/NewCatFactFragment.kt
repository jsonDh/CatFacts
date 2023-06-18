package com.json.catfacts.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.json.catfacts.databinding.FragmentNewCatFactBinding
import com.json.catfacts.presentation.viewmodels.APICatFactViewModel

class NewCatFactFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewCatFactBinding
    private lateinit var viewModel: APICatFactViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[APICatFactViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNewCatFactBinding.inflate(inflater, container, false)
        return binding.root
    }

}