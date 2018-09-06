package com.example.isaqu.marvelheroes.model

import com.squareup.moshi.Json
import java.util.*

class Character {

    @Json(name = "id")
    var id: Int? = null
    @Json(name = "name")
    var name: String? = null
    @Json(name = "description")
    var description: String? = null
    @Json(name = "modified")
    var modified: String? = null
    @Json(name = "resourceURI")
    var resourceURI: String? = null
    @Json(name = "urls")
    var urls: List<Url> = ArrayList<Url>()


    class Url {
        @Json(name = "type")
        var type: String? = null
        @Json(name = "url")
        var url: String? = null
    }

//    class Stories {
//        private var available: Int? = null
//        private var collectionURI: String? = null
//        private var items: List<Item__> = ArrayList<Item__>()
//        private var returned: Int? = null
//    }
}