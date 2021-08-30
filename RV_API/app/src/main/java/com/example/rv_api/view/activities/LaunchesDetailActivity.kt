package com.example.rv_api.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rv_api.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_launches_detail.*

class LaunchesDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launches_detail)

        val actionBar = supportActionBar
        actionBar?.title = "Space X - Launch Details"

        val name = intent.getStringExtra("name").toString()
        val details = intent.getStringExtra("details").toString()
        val flightNumber = intent.getStringExtra("flightNumber").toString()

        tv_flight_name_detail.text = name
        tv_flight_number_detail.text = flightNumber
        tv_details_detail.text = details

        val launchImage = intent.getStringExtra("image").toString()

        when (launchImage) {
            "No image" -> {
                iv_flight_image_detail.setImageResource(R.drawable.default_launch)
            }
            else -> {
                Picasso.with(this)
                    .load(launchImage)
                    .resize(800,800)
                    .centerInside()
                    .into(iv_flight_image_detail)
            }
        }
    }
}