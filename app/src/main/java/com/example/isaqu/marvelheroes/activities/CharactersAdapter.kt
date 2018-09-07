package com.example.isaqu.marvelheroes.activities

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.isaqu.marvelheroes.R
import com.example.isaqu.marvelheroes.model.Character

class CharactersAdapter(context: Context, mCharacters: List<Character>) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private var charactersList: List<Character>
    private var mContext: Context

    init {
        charactersList = mCharacters
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(charactersList[position])
    }


    fun setCharacters(characters: List<Character>) {
        this.charactersList = characters
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView
        private val photo: ImageView
//        private var card: CardView

        init {
            name = itemView.findViewById(R.id.character_name)
            photo = itemView.findViewById(R.id.character_photo)
//            card = itemView.findViewById(R.id.character_card)

        }

        fun bind(character: Character) {
            name.text = character.name

//
//            val posterPath = movie.posterPath
//            if (TextUtils.isEmpty(posterPath) == false) {
//                Glide.with(itemView)
//                        .load(Utils.buildPosterUrl(posterPath))
//                        .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
//                        .into(posterImageView)
//            }
//
//            card.setOnClickListener{this}
        }


//        fun onClick(view: View) {
//            if (view.id == R.id.character_card) {
//                val intent = Intent(view.context, CharacterDetailActivity::class.java)
//                view.context.startActivity(intent)
//            }
//        }

    }
}