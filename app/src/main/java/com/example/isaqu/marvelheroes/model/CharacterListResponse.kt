package com.example.isaqu.marvelheroes.model

import com.squareup.moshi.Json
import java.util.*

class CharacterListResponse {

    @Json(name = "code")
    var code: Int? = null
    @Json(name = "status")
    var status: String? = null
    @Json(name = "copyright")
    var copyright: String? = null
    @Json(name = "attributionText")
    var attributionText: String? = null
    @Json(name = "attributionHTML")
    var attributionHTML: String? = null
    @Json(name = "etag")
    var etag: String? = null
    @Json(name = "data")
    var data: Data? = null

    class Data {
        @Json(name = "offset")
        var offset: Int? = null
        @Json(name = "limit")
        var limit: Int? = null
        @Json(name = "total")
        var total: Int? = null
        @Json(name = "count")
        var count: Int? = null
        @Json(name = "results")
        var characters: List<Character> = ArrayList()

    }
}