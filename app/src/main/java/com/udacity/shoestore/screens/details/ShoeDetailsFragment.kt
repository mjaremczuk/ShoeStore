package com.udacity.shoestore.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.screens.ShoeViewModel

class ShoeDetailsFragment : Fragment() {

    private var _binding: FragmentShoeDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoeDetailsBinding.inflate(inflater, container, false)

        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.itemAdded.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigateUp()
                viewModel.onSaveClickComplete()
            }
        })

        viewModel.cancelled.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigateUp()
                viewModel.onCancelClickComplete()
            }
        })

        viewModel.invalidForm.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), getString(R.string.details_all_fields_required), Toast.LENGTH_SHORT).show()
            }
        })

        binding.detailsSave.setOnClickListener {
            viewModel.onSaveClick()
        }
        binding.detailsCancel.setOnClickListener {
            viewModel.onCancelClick()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}