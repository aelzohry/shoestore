package com.udacity.aelzohry.shoestore.screens.shoelist

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.udacity.aelzohry.shoestore.R
import com.udacity.aelzohry.shoestore.databinding.FragmentShoeListBinding
import com.udacity.aelzohry.shoestore.databinding.ViewShoeItemBinding
import com.udacity.aelzohry.shoestore.models.Shoe
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    // initialize shared view model and let the activity be the owner
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("List size: ${viewModel.shoeList.value?.size}")

        addMenu()

        viewModel.shoeList.observe(viewLifecycleOwner) { shoeList ->
            updateShoeList(shoeList)
        }

        viewModel.addShoeEvent.observe(viewLifecycleOwner) {
            if (it) goToAddShoeScreen()
        }
    }

    // add menu to action bar
    private fun addMenu() {
        val menuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shoe_list_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_action_logout -> {
                        onLogout()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun updateShoeList(shoeList: MutableList<Shoe>) {
        val listLayout = binding.listLinearLayout

        /* remove old shoe list
         I think we might enhance it by adding the last added shoe only */
        listLayout.removeAllViews()

        // add updated shoe list
        for (shoe in shoeList) {
            // inflate shoe item view
            val shoeView = ViewShoeItemBinding.inflate(layoutInflater)

            // pass the shoe model and let the magic happens with data binding :)
            shoeView.shoe = shoe

            // finally append the view to linear layout
            listLayout.addView(
                shoeView.root,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
            )

            // remove the item when the user long presses the shoe item
            shoeView.root.setOnLongClickListener {
                viewModel.removeShoe(shoe)
                true
            }
        }
    }

    private fun goToAddShoeScreen() {
        val destination = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment()
        findNavController().navigate(destination)
        viewModel.addShoeEventCompleted()
    }

    // when user presses logout
    private fun onLogout() {
        // go back to login screen
        val destination = ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
        findNavController().navigate(destination)
    }

}