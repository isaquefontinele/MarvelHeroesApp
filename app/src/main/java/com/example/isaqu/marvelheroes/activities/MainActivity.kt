package com.example.isaqu.marvelheroes.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.isaqu.marvelheroes.R
import com.example.isaqu.marvelheroes.data.MarvelApi
import com.example.isaqu.marvelheroes.model.Character
import com.example.isaqu.marvelheroes.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var api: MarvelApi
    private lateinit var mCharacters: ArrayList<Character>
    private lateinit var mAdapter: CharactersAdapter
    private lateinit var progressDialog: ProgressDialog
    private var skip: Int = 0
    private var mCurrentQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setupFields()

        startApi()
        loadCharacters()
    }

    override fun onCreateView(parent: View?, name: String?, context: Context?, attrs: AttributeSet?): View {
        val toolbar = parent!!.findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        setupSearchBar(parent)
        return super.onCreateView(parent, name, context, attrs)
    }

    private fun startApi() {
        api = Retrofit.Builder()
                .baseUrl(Constants.URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MarvelApi::class.java)
    }

    private fun setupSearchBar(parent: View) {
        val searchView = parent.findViewById<SearchView>(R.id.search_characters)
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                resetListParameters()
                mCurrentQuery = newText
                if (!newText.isEmpty()) {
                    loadCharactersByName()
                } else {
                    loadCharacters()
                }
                return false
            }
        })
        mRecyclerView.requestFocus()
    }

    private fun resetListParameters() {
        skip = 0
        mCharacters.clear()
    }

    private fun setupFields() {
        mCharacters = ArrayList()
        progressDialog = ProgressDialog(this)
        mAdapter = CharactersAdapter(mCharacters) {
            //OnClick on card
            val intent = Intent(this, CharacterDetailActivity::class.java)
            intent.putExtra(Constants.CHARACTER_DATA, mCharacters[it] as Serializable)
            startActivity(intent)
        }

        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.adapter = mAdapter

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!isLoading()) {
                    if (!recyclerView!!.canScrollVertically(1)) {
                        if (!mCurrentQuery.isEmpty()) {
                            loadCharacters()
                        } else {
                            loadCharactersByName()
                        }
                    }
                }
            }
        })
    }

    private fun loadCharactersByName() {
        showLoading()
        api.listCharactersByName(Constants.TS, Constants.API_KEY, Constants.HASH_KEY, Constants.LIMIT, skip.toString(), mCurrentQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    mCharacters.addAll(response.data!!.characters)
                    mAdapter.setCharacters(mCharacters)
                    hideLoading()
                    skip += 15
                }
    }

    private fun loadCharacters() {
        showLoading()
        api.listCharacters(Constants.TS, Constants.API_KEY, Constants.HASH_KEY, Constants.LIMIT, skip.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    mCharacters.addAll(response.data!!.characters)
                    mAdapter.setCharacters(mCharacters)
                    hideLoading()
                    skip += 15
                }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    internal fun showLoading() {
        progressDialog.setTitle(getString(R.string.loading))
        progressDialog.setMessage(getString(R.string.wait_while_loading))
        progressDialog.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progressDialog.show()
    }

    internal fun hideLoading() {
        progressDialog.dismiss()
    }

    internal fun isLoading(): Boolean {
        return progressDialog.isShowing
    }
}
