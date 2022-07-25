package com.udacity.aelzohry.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.aelzohry.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Connecting navigation with action bar
        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        // add destination change listener to hide up button when replacing the startDestination
        navController.addOnDestinationChangedListener { navController, _, _ ->
            val shouldShowUpButton = navController.previousBackStackEntry != null
            supportActionBar?.setDisplayHomeAsUpEnabled(shouldShowUpButton)
        }
    }

    // ActionBar up button action
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}