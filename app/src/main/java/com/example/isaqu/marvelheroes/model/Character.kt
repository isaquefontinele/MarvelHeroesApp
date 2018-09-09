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
    @Json(name = "urls")
    var urls: List<Url> = ArrayList<Url>()


    class Url : Serializable {
        @Json(name = "type")
        var type: String? = null
        @Json(name = "url")
        var url: String? = null
    }

    class Thumbnail : Serializable {
        @Json(name = "path")
        var path: String? = null
        @Json(name = "extension")
        var extension: String? = null
    }
}