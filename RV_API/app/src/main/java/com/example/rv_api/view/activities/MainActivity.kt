package com.example.rv_api.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rv_api.CrewAdapter
import com.example.rv_api.R
import com.example.rv_api.LaunchesAdapter
import com.example.rv_api.viewmodel.SpaceXViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val spaceXViewModel: SpaceXViewModel by viewModels()

    var logoVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = "Space X"

        val upcomingList = spaceXViewModel.fetchUpcomingData()
        spaceXViewModel.fetchPreviousData()
        spaceXViewModel.fetchCrewData()

        spaceXViewModel.upcomingLaunchesList.observe(this, Observer {


            home_recyclerview.layoutManager = LinearLayoutManager(this)
            home_recyclerview.adapter = LaunchesAdapter(upcomingList)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.home_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.crew_menu_option -> {

                if (logoVisible) showHideHeader(logo_header)
                logoVisible = false

                val crewList = spaceXViewModel.crewList.value

                if (crewList != null) {
                    home_recyclerview.layoutManager = GridLayoutManager(this, 2)
                    home_recyclerview.adapter = CrewAdapter(crewList)
                } else Toast.makeText(this, "No Crew Data", Toast.LENGTH_SHORT).show()
            }

            R.id.upcoming_menu_option -> {

                upcoming_past_launches_tv.text = getString(R.string.upcoming)
                launches_main_tv.text = getString(R.string.launches)

                if(!logoVisible) showHideHeader(logo_header)
                logoVisible = true

                val upcomingList = spaceXViewModel.upcomingLaunchesList.value
                if(upcomingList != null) {
                    home_recyclerview.layoutManager = LinearLayoutManager(this)
                    home_recyclerview.adapter = LaunchesAdapter(upcomingList)
                }
            }
            R.id.pastLaunches_menu_option -> {

                upcoming_past_launches_tv.text = getString(R.string.past)
                launches_main_tv.text = getString(R.string.launches)

                if(!logoVisible) showHideHeader(logo_header)
                logoVisible = true

                val pastList = spaceXViewModel.previousLaunchesList.value
                if(pastList != null) {
                    home_recyclerview.layoutManager = LinearLayoutManager(this)
                    home_recyclerview.adapter = LaunchesAdapter(pastList)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showHideHeader(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.GONE
        } else{
            View.VISIBLE
        }
    }
}