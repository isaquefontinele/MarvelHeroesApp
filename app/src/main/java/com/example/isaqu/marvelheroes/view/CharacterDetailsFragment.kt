package com.example.isaqu.marvelheroes.view

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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

        val name = rootView.findViewById(R.id.value_name) as TextView
        val details = rootView.findViewById(R.id.value_details) as TextView
        val comics = rootView.findViewById(R.id.value_comics) as TextView
        val stories = rootView.findViewById(R.id.value_stories) as TextView
        val events = rootView.findViewById(R.id.value_events) as TextView
        val series = rootView.findViewById(R.id.value_series) as TextView

        if (mCharacter != null) {
            name.text = mCharacter.name
            val noInfo = getString(R.string.nothing_found)
            details.text = if (mCharacter.description!!.isEmpty()) noInfo else mCharacter.description
            comics.text = if (mCharacter.comics!!.items.isEmpty()) noInfo else getFormattedList(mCharacter.comics!!.items)
            stories.text = if (mCharacter.stories!!.items.isEmpty()) noInfo else getFormattedList(mCharacter.stories!!.items)
            events.text = if (mCharacter.events!!.items.isEmpty()) noInfo else getFormattedList(mCharacter.events!!.items)
            series.text = if (mCharacter.series!!.items.isEmpty()) noInfo else getFormattedList(mCharacter.series!!.items)
        }
        return rootView
    }

    private fun getFormattedList(itemsList: List<Character.Item>): CharSequence? {
        val formatedList = StringBuilder()
        for (item in itemsList) {
            formatedList.append(item.name).append("\n")
        }
        return formatedList.toString()
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