package com.example.isaqu.marvelheroes.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.Toolbar
import com.example.isaqu.marvelheroes.R
import com.example.isaqu.marvelheroes.model.Character
import com.example.isaqu.marvelheroes.utils.Constants
import com.example.isaqu.marvelheroes.view.CharacterDetailsFragment

class CharacterDetailActivity : BaseActivity() {

    private lateinit var instanceState: Bundle
    private lateinit var mCharacter: Character

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        instanceState = savedInstanceState!!
        setContentView(R.layout.activity_character_details)
        val toolbar = findViewById<Toolbar>(R.id.detail_toolbar)
        setSupportActionBar(toolbar)

        // Show the Up button in the action bar.
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        setupFragment(savedInstanceState)
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        mCharacter = getIntent().extras.getSerializable(Constants.CHARACTER_DATA) as Character
        if (savedInstanceState == null) {
            val arguments = Bundle()
            arguments.putSerializable(Constants.CHARACTER_DATA, mCharacter)
            val fragment = CharacterDetailsFragment()
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction()
                    .add(R.id.character_detail_container, fragment)
                    .commit()
        }
    }
}