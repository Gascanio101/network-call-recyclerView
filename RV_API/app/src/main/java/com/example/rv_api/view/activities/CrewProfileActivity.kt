package com.example.rv_api.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rv_api.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_crew_profile.*

class CrewProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crew_profile)

        supportActionBar?.title = "Space X - Profile"

        val name = intent.getStringExtra("name").toString()
        val agency = intent.getStringExtra("agency").toString()
        val status = intent.getStringExtra("status").toString()

        tv_profile_name.text = name
        tv_profile_agency.text = agency
        tv_profile_status.text = status

        val profilePictureUrl = intent.getStringExtra("image").toString()
        Picasso.with(this)
            .load(profilePictureUrl)
            .resize(800,800)
            .centerCrop()
            .into(iv_crew_profile)
    }
}