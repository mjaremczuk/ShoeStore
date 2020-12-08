package com.udacity.shoestore.screens.list

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.screens.ShoeViewModel

class ShoeFragment : Fragment() {

    private var _binding: FragmentShoesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoes,
            container,
            false
        )
        setHasOptionsMenu(true)

        viewModel.shoes.observe(viewLifecycleOwner) {
            it.forEachIndexed { index, shoe ->
                val item = DataBindingUtil.inflate<ShoeItemBinding>(
                    inflater,
                    R.layout.shoe_item,
                    container,
                    false
                )
                item.shoe = shoe
                item.root.id = index
                binding.shoesList.addView(item.root)
            }
        }

        binding.shoesAddNew.setOnClickListener {
            requireView().findNavController().navigate(ShoeFragmentDirections.createNewShoe())
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.sign_out_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.loginFragment -> {
            requireView().findNavController().navigate(ShoeFragmentDirections.logOut())
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}