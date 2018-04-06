package com.example.tomo.yamaapp.model.data

import java.io.Serializable

/**
 * Created by tomo on 2018/04/06.
 */
data class Diary(val id: Int, val image: Image?, val title: String): Serializable {
    data class Image(val thumbnailUrl: String) : Serializable
}