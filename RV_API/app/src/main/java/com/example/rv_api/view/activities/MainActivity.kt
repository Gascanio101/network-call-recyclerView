package com.example.rv_api.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rv_api.models.crew.CrewAdapter
import com.example.rv_api.R
import com.example.rv_api.models.launches.LaunchesAdapter
import com.example.rv_api.viewmodel.SpaceXViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val spaceXViewModel: SpaceXViewModel by viewModels()

    var isSorted = false
    var currentListId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set initial title for home action bar
        val actionBar = supportActionBar
        actionBar!!.title = "Space X - Upcoming launches"

        // Load all network calls
        val upcomingList = spaceXViewModel.fetchUpcomingData()
        spaceXViewModel.fetchPreviousData()
        spaceXViewModel.fetchCrewData()

        // Set home screen to show upcoming launches when it loads.
        spaceXViewModel.upcomingLaunchesList.observe(this, Observer {
            home_recyclerview.layoutManager = LinearLayoutManager(this)
            home_recyclerview.adapter = LaunchesAdapter(upcomingList)
        })
    }

    // Inflate menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val pastList = spaceXViewModel.previousLaunchesList.value
        val crewList = spaceXViewModel.crewList.value
        val upcomingList = spaceXViewModel.upcomingLaunchesList.value
        val actionBar = supportActionBar

        when (item.itemId) {

            R.id.upcoming_menu_option -> {

                actionBar!!.title = "Space X - Upcoming launches"

                isSorted = false

                if(upcomingList != null) {
                    home_recyclerview.layoutManager = LinearLayoutManager(this)
                    home_recyclerview.adapter = LaunchesAdapter(upcomingList)
                    currentListId = 0
                } else Toast.makeText(this, "No upcoming launches Data", Toast.LENGTH_SHORT).show()
            }

            R.id.pastLaunches_menu_option -> {

                isSorted = false

                actionBar!!.title = "Space X - Past launches"

                if(pastList != null) {
                    home_recyclerview.layoutManager = LinearLayoutManager(this)
                    home_recyclerview.adapter = LaunchesAdapter(pastList)
                    currentListId = 1
                } else Toast.makeText(this, "No past launches Data", Toast.LENGTH_SHORT).show()
            }

            R.id.crew_menu_option -> {

                actionBar!!.title = "Space X - Crew"

                isSorted = false

                if (crewList != null) {
                    home_recyclerview.layoutManager = GridLayoutManager(this, 2)
                    home_recyclerview.adapter = CrewAdapter(crewList)
                    currentListId = 2
                } else Toast.makeText(this, "No Crew Data", Toast.LENGTH_SHORT).show()
            }

            R.id.sort_menu_option -> {
                when (currentListId) {
                    0 -> {
                        if (!upcomingList.isNullOrEmpty()){
                            if (isSorted) {
                                upcomingList.sortByDescending { it.name }
                                home_recyclerview.adapter = LaunchesAdapter(upcomingList)
                            }
                            else {
                                upcomingList.sortBy { it.name }
                                home_recyclerview.adapter = LaunchesAdapter(upcomingList)
                            }
                        }
                    }
                    1 -> {
                        if (!pastList.isNullOrEmpty()){
                            if (isSorted) {
                                pastList.sortByDescending { it.name }
                                home_recyclerview.adapter = LaunchesAdapter(pastList)
                            }
                            else {
                                pastList.sortBy { it.name }
                                home_recyclerview.adapter = LaunchesAdapter(pastList)
                            }
                        }
                    }
                    2 -> {
                        if (!crewList.isNullOrEmpty()){
                            if (isSorted) {
                                crewList.sortByDescending { it.name }
                                home_recyclerview.adapter = CrewAdapter(crewList)
                            }
                            else {
                                crewList.sortBy { it.name }
                                home_recyclerview.adapter = CrewAdapter(crewList)
                            }
                        }
                    }
                }
                isSorted = !isSorted
            }
        }
        return super.onOptionsItemSelected(item)
    }
}