package com.example.rv_api.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rv_api.LaunchesAdapter
import com.example.rv_api.SpaceXService
import com.example.rv_api.models.crew.Crew
import com.example.rv_api.models.launches.upcoming.LaunchItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpaceXViewModel : ViewModel () {
    val upcomingLaunchesList: MutableLiveData<MutableList<LaunchItem>> by lazy {
        MutableLiveData<MutableList<LaunchItem>>()
    }

    val previousLaunchesList: MutableLiveData<MutableList<LaunchItem>> by lazy {
        MutableLiveData<MutableList<LaunchItem>>()
    }

    val crewList: MutableLiveData<MutableList<Crew.Astronaut>> by lazy {
        MutableLiveData<MutableList<Crew.Astronaut>>()
    }


    fun fillUpcomingLaunches(_tempList: MutableList<LaunchItem>) {
        upcomingLaunchesList.value = _tempList
    }

    fun fillPreviousLaunches(_tempList: MutableList<LaunchItem>) {
        previousLaunchesList.value = _tempList
    }

    fun fillCrewData(_tempList: MutableList<Crew.Astronaut>) {
        crewList.value = _tempList
    }

    fun fetchUpcomingData(): MutableList<LaunchItem> {

        val tempList = mutableListOf<LaunchItem>()

        CoroutineScope(Dispatchers.IO).launch {

            val response = SpaceXService.getSpaceXDataService()
                .getUpcomingLaunches()

            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    val tempResponse = response.body()

                    tempResponse?.forEach {
                        tempList.add(LaunchItem(
                            it.details ?: "No Description",
                            it.flightNumber,
                            it.id,
                            it.launchpad,
                            it.name,
                            it.links))
                    }
                    fillUpcomingLaunches(tempList)
                }
            }
        }
        return tempList
    }

    fun fetchPreviousData(): MutableList<LaunchItem> {

        val tempList = mutableListOf<LaunchItem>()

        CoroutineScope(Dispatchers.IO).launch {

            val response = SpaceXService.getSpaceXDataService()
                .getPastLaunches()

            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    val tempResponse = response.body()

                    tempResponse?.forEach {
                        tempList.add(LaunchItem(
                            it.details ?: "No Description",
                            it.flightNumber,
                            it.id,
                            it.launchpad,
                            it.name,
                            it.links))
                    }

                    fillPreviousLaunches(tempList)
                }
            }
        }
        return tempList
    }

    fun fetchCrewData(): MutableList<Crew.Astronaut> {

        val tempList = mutableListOf<Crew.Astronaut>()

        CoroutineScope(Dispatchers.IO).launch {

            val response = SpaceXService.getSpaceXDataService()
                .getCrewList()

            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    val tempResponse = response.body()

                    tempResponse?.forEach {
                        tempList.add(Crew.Astronaut(
                            it.name,
                            it.agency,
                            it.image,
                            it.status,
                            it.id))
                    }
                    fillCrewData(tempList)
                }
            }
        }
        return tempList
    }
}