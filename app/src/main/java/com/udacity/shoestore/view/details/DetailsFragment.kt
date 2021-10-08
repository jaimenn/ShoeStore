package com.udacity.shoestore.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentDetailsBinding
import com.udacity.shoestore.view.shoes.SaveState
import com.udacity.shoestore.data.models.Shoe
import com.udacity.shoestore.view.shoes.ShoesViewModel

class DetailsFragment : Fragment() {

    private val viewModel : ShoesViewModel by activityViewModels()

    private val shoe = Shoe("", 0.0, "", "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentDetailsBinding.inflate(
            inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.shoe = shoe

        binding.cancelButton.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment()
            findNavController().navigate(action)
        }

        viewModel.saveState.observe(this as LifecycleOwner, { ss ->
            when(ss) {
                SaveState.SAVE -> {
                    navigateToShoeList()
                    viewModel.onEventSaveComplete()
                }
            }
        })

        return binding.root
    }

    private  fun navigateToShoeList() {
        val action = DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment()
        findNavController().navigate(action)

    }

}