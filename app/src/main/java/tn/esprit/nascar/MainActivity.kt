package tn.esprit.nascar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import tn.esprit.nascar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar.id


        //TODO 11 Bind the toolbar to the activity
        supportActionBar?.setTitle("Nascar")

        setSupportActionBar(binding.toolbar)
        //TODO 1 Implement the click on the 3 buttons (btnNews|btnEvents|btnProfile) to call changeFragment(...)
        binding.btnNews.setOnClickListener() {
            val newsfragment = NewsFragment()
            changeFragment(newsfragment, "NewsFragment")
        }
        binding.btnEvents.setOnClickListener() {
            val eventsfragment = EventsFragment()
            changeFragment(eventsfragment, "eventsfragment")
        }
        binding.btnProfile.setOnClickListener() {
            val newsfragment = NewsFragment()
            changeFragment(newsfragment, "profilefragment")
        }

        //TODO 2 Implement the first call of the first fragment
        if (savedInstanceState == null) {
            val intiaFragment = NewsFragment()
            changeFragment(intiaFragment, "ProfileFragment")
        }
    }

    private fun changeFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fragmentContainer.id, fragment)



        transaction.commit()
    }

    //TODO 12 Override the method onCreateOptionsMenu()
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    //TODO 13 Override the method onOptionsItemSelected() and Implement info and logout behavior
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_info -> {
                val aboutfragment = AboutFragment()
                changeFragment(aboutfragment, "aboutfragment")

            }

            R.id.logoutMenu -> {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Logout")
                alertDialogBuilder.setMessage("Are you sure you want to log out ?")
                alertDialogBuilder.setPositiveButton("Oui") { dialog, _ ->
                    finish()
                }
                alertDialogBuilder.setNegativeButton("Non") { dialog, _ ->
                    dialog.dismiss()
                }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}



