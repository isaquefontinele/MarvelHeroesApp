package com.example.isaqu.marvelheroes.utils

import com.example.isaqu.marvelheroes.model.Character

class Utils {

    companion object {
        fun getImagePath(thumbnail: Character.Thumbnail, size: Int): String {
            if (size.equals(1)) {
                return thumbnail.path + "/standard_amazing." + thumbnail.extension
            } else {
                return thumbnail.path + "/landscape_incredible." + thumbnail.extension
            }
        }
    }

}