package com.udacity.shoestore.view.shoes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesBinding
import com.udacity.shoestore.databinding.ShoeRowBinding
import timber.log.Timber


class ShoesFragment : Fragment() {

    private val viewModel : ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val binding : FragmentShoesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoes, container, false)

        viewModel.shoes.observe(this as LifecycleOwner, Observer {

            for (shoe in viewModel.shoes.value!!) {
                val inBinding = ShoeRowBinding.inflate(layoutInflater)
                inBinding.shoe = shoe
                binding.innerLayout.addView(inBinding.root)
            }
        })

        binding.addShoeButton.setOnClickListener {
            Timber.i("in addShoeButton listener")
            Timber.i(viewModel.shoes.value?.joinToString(separator = "\n"))
            val action = ShoesFragmentDirections.actionShoesFragmentToDetailsFragment()
            findNavController().navigate(action)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    

}