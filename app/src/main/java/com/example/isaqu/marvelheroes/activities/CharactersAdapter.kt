package com.example.isaqu.marvelheroes.activities

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.isaqu.marvelheroes.R
import com.example.isaqu.marvelheroes.model.Character
import com.example.isaqu.marvelheroes.utils.Utils

class CharactersAdapter(var mCharacters: List<Character>, val listener: (Int) -> Unit) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mCharacters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(mCharacters[position], position, listener)

    fun setCharacters(characters: List<Character>) {
        mCharacters = characters
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView
        private val photo: ImageView
        private var card: CardView

        init {
            name = itemView.findViewById(R.id.character_name)
            photo = itemView.findViewById(R.id.character_photo)
            card = itemView.findViewById(R.id.character_card)
        }

        fun bind(character: Character, position: Int, listener: (Int) -> Unit) {
            name.text = character.name

            val imagePath = character.thumbnail!!.path
            if (TextUtils.isEmpty(imagePath) == false) {
                Glide.with(itemView)
                        .load(Utils.getImagePath(character.thumbnail!!, 1))
                        .into(photo)
            }

            card.setOnClickListener { listener(position) }
        }

    }
}