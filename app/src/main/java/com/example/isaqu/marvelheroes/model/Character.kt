package com.example.isaqu.marvelheroes.model

import com.squareup.moshi.Json
import java.io.Serializable
import java.util.*

class Character : Serializable {

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
    @Json(name = "thumbnail")
    var thumbnail: Thumbnail? = null
    @Json(name = "comics")
    var comics: Comics? = null
    @Json(name = "series")
    var series: Series? = null
    @Json(name = "stories")
    var stories: Stories? = null
    @Json(name = "events")
    var events: Events? = null


    class Thumbnail : Serializable {
        @Json(name = "path")
        var path: String? = null
        @Json(name = "extension")
        var extension: String? = null
    }

    class Events : Serializable {
        @Json(name = "available")
        private var available: Int? = null
        @Json(name = "collectionURI")
        private var collectionURI: String? = null
        @Json(name = "items")
        var items: List<Item> = ArrayList()
        @Json(name = "returned")
        private var returned: Int? = null
    }

    class Comics : Serializable {
        @Json(name = "available")
        private var available: Int? = null
        @Json(name = "collectionURI")
        private var collectionURI: String? = null
        @Json(name = "items")
        var items: List<Item> = ArrayList<Item>()
        @Json(name = "returned")
        private var returned: Int? = null
    }

    class Stories : Serializable {
        @Json(name = "available")
        private var available: Int? = null
        @Json(name = "collectionURI")
        private var collectionURI: String? = null
        @Json(name = "items")
        var items: List<Item> = ArrayList<Item>()
        @Json(name = "returned")
        private var returned: Int? = null
    }

    class Series : Serializable {
        @Json(name = "available")
        private var available: Int? = null
        @Json(name = "collectionURI")
        private var collectionURI: String? = null
        @Json(name = "items")
        var items: List<Item> = ArrayList<Item>()
        @Json(name = "returned")
        private var returned: Int? = null
    }

    class Item : Serializable {
        @Json(name = "name")
        var name: String? = null
    }
}