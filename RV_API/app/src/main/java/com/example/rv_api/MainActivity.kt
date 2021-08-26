package com.example.rv_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rv_api.models.launches.upcoming.UpcomingItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val upcomingLaunchesList: MutableLiveData<UpcomingItem> by lazy {
        MutableLiveData<UpcomingItem>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val upcomingList = listOf<UpcomingItem>()

        fetchData()

        upcoming_recyclerView.layoutManager = LinearLayoutManager(this)
        upcoming_recyclerView.adapter = UpcomingAdapter(upcomingList)
    }

    private fun fetchData() : List<UpcomingItem> {

        var returnList = listOf<UpcomingItem>()

        CoroutineScope(Dispatchers.IO).launch {

            val response = SpaceXService
                .getSpaceXDataService()
                .getUpcomingLaunches()

            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    val tempResponse = response.body()
                    val tempList = mutableListOf<UpcomingItem>()

                    tempResponse?.forEach {
                        tempList.add(UpcomingItem(
                            it.details,
                            it.flightNumber,
                            it.id,
                            it.launchpad,
                            it.name))
                    }
                    returnList = tempList
                }
            }
        }
        return returnList
    }
}