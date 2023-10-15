package tn.esprit.nascar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nascar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var newRecyclerView : RecyclerView
    private lateinit var newArrayList: ArrayList<Nascardata>
    private lateinit var binding : ActivityMainBinding
     lateinit var imageId:Array<Int>
     lateinit var titleN:Array<String>
    lateinit var descrptionN:Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageId = arrayOf(
            R.drawable.ic_event1,
            R.drawable.ic_event2,
            R.drawable.ic_event3,
            R.drawable.ic_event4,
            R.drawable.ic_news1,
            R.drawable.ic_news2,
            R.drawable.ic_news3


        )
        titleN = arrayOf(
            "wassim 1",
            "wassim2",
            "wassim 1",
            "wassim2",
            "wassim 1",
            "wassim2",
            "wassim 1"


        )
        descrptionN = arrayOf(
            "25 mars 2000",
            "25 mars 2000",
            "25 mars 2000",
            "25 mars 2000",
            "25 mars 2000",
            "25 mars 2000",
            "25 mars 2000"
        )
       // newRecyclerView = findViewById(R.id.imgNascar)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<Nascardata>()
        getNasacardata()



        val toolbar: Toolbar = binding.toolbar.appBar
        setSupportActionBar(toolbar)

        binding.btnNews.setOnClickListener {
            changeFragment(NewsFragment(), "")
        }

        binding.btnEvents.setOnClickListener {
            changeFragment(EventsFragment(), "")
        }

        binding.btnProfile.setOnClickListener {
            changeFragment(ProfileFragment(), "")
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, NewsFragment()).commit()
    }
    private fun getNasacardata(){
            for(i in imageId.indices){
                val nasdata = Nascardata(imageId[i],titleN[i],descrptionN[i] )
                newArrayList.add(nasdata)

            }
        newRecyclerView.adapter = RecycleNascar(newArrayList )
    }

    private fun changeFragment(fragment: Fragment, name: String) {

        if (name.isEmpty())
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        else
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("").commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.infoMenu -> {
                changeFragment(AboutFragment(),"AboutMe")
            }
            R.id.logoutMenu ->{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Logout")
                builder.setMessage("Are you sure you want to logout ?")
                builder.setPositiveButton("Yes"){ dialogInterface, which ->
                    finish()
                }
                builder.setNegativeButton("No"){dialogInterface, which ->
                    dialogInterface.dismiss()
                }
                builder.create().show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}