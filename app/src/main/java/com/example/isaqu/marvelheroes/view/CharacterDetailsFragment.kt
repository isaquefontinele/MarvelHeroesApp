package com.example.isaqu.marvelheroes.view

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.isaqu.marvelheroes.R
import com.example.isaqu.marvelheroes.model.Character
import com.example.isaqu.marvelheroes.utils.Constants
import com.example.isaqu.marvelheroes.utils.Utils
import com.squareup.picasso.Picasso

class CharacterDetailsFragment : Fragment() {

    private lateinit var mCharacter: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments!!.containsKey(Constants.CHARACTER_DATA)) {
            mCharacter = arguments!!.getSerializable(Constants.CHARACTER_DATA) as Character
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.character_detail, container, false)
        setUpToolbar()

        return rootView
    }

    private fun setUpToolbar() {
        val activity = this.activity
        val appBarLayout = activity!!.findViewById(R.id.toolbar_layout) as CollapsingToolbarLayout
        val moviePosterHeader = appBarLayout.findViewById(R.id.movie_poster_large) as ImageView

        if (mCharacter != null) {
            Picasso.with(context).load(Utils.getImagePath(mCharacter.thumbnail!!, 2)).placeholder(R.drawable.marvel).into(moviePosterHeader)

        }
    }
}