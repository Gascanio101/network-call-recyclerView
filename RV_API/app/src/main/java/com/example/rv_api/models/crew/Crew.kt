package com.example.rv_api.models.crew

class Crew: ArrayList<Crew.Astronaut>() {

    data class Astronaut(val name: String,
                         val agency: String,
                         val image: String?,
                         val status: String,
                         val id: String)
}

/*
"name": "Robert Behnken",
"agency": "NASA",
"image": "https://imgur.com/0smMgMH.png",
"wikipedia": "https://en.wikipedia.org/wiki/Robert_L._Behnken",
"launches": [
"5eb87d46ffd86e000604b388"
],
"status": "active",
"id": "5ebf1a6e23a9a60006e03a7a"*/
