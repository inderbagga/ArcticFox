package com.inderbagga.fox

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.inderbagga.fox.databinding.ActivityFoxBinding

class FoxActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityFoxBinding
    private var foxMenu: Menu? = null
    private  var activeFragmentId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val navController = findNavController(R.id.hostFragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        //Marking Top-Level destinations of a Navigation Graph
        appBarConfiguration = AppBarConfiguration(
            setOf( R.id.homeFragment, R.id.detailFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.hostFragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {

        when(activeFragmentId){

            R.id.homeFragment -> foxMenu?.let { onOptionsItemSelected(it.findItem(R.id.logout)) }
            else -> onFragmentChange(R.id.homeFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.fox_menu, menu)
        this.foxMenu=menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.logout -> {

                val snack = Snackbar.make(binding.root,"Do you want to logout?", Snackbar.LENGTH_LONG)
                snack.setAction("Proceed") {
                    onFragmentChange(R.id.loginFragment)
                }

                val view = snack.view
                val params = view.layoutParams as CoordinatorLayout.LayoutParams
                params.gravity = Gravity.TOP
                view.layoutParams = params
                snack.show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun onFragmentChange(fragmentId : Int) {

        activeFragmentId = fragmentId
        findNavController(R.id.hostFragment).navigate(activeFragmentId, null)

        when(activeFragmentId){
            R.id.loginFragment -> {
                supportActionBar?.hide()
            }
            R.id.homeFragment -> supportActionBar?.show()
        }
    }
}