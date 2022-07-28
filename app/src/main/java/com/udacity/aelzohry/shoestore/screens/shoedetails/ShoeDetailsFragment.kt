package com.udacity.aelzohry.shoestore.screens.shoedetails

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.aelzohry.shoestore.R
import com.udacity.aelzohry.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.aelzohry.shoestore.extensions.hideKeyboard
import com.udacity.aelzohry.shoestore.screens.shoelist.ShoeListViewModel

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding

    // shoe details view model for the logic that happens here only like validating inputs
    private val viewModel: ShoeDetailsViewModel by viewModels()

    // shared view model with shoe list
    private val shoeListViewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.validationFailedEvent.observe(viewLifecycleOwner) {
            if (it) onValidationFailed()
        }

        viewModel.saveEvent.observe(viewLifecycleOwner) {
            if (it) onAdd()
        }

        viewModel.cancelEvent.observe(viewLifecycleOwner) {
            if (it) onCancel()
        }
    }

    private fun onValidationFailed() {
        viewModel.onValidationFailedEventCompleted()

        hideKeyboard()

        // alert user of missing fields
        Toast.makeText(
            requireContext(),
            getString(R.string.complete_all_fields),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onAdd() {
        viewModel.onSaveEventCompleted()

        hideKeyboard()

        // now validation is completed, and shoe is ready to be added to the shoe list
        val shoe = viewModel.shoe.value
        shoe?.let {
            shoeListViewModel.addShoe(it)
            // back to shoe list screen
            findNavController().navigateUp()
        }
    }

    private fun onCancel() {
        viewModel.onCancelEventCompleted()
        findNavController().navigateUp()
    }

}