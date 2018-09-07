package com.example.isaqu.marvelheroes.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.isaqu.marvelheroes.R
import com.example.isaqu.marvelheroes.data.MarvelApi
import com.example.isaqu.marvelheroes.model.Character
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class MainActivity : BaseActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var api: MarvelApi
    private lateinit var mCharacters: List<Character>
    private lateinit var mAdapter: CharactersAdapter
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFields()
        setupSearchBar()
        loadCharacters()

    }

    private fun setupSearchBar() {
    }


    private fun setupFields() {
        mCharacters = ArrayList<Character>()
        progressDialog = ProgressDialog(this)
        mAdapter = CharactersAdapter(this, mCharacters)
        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.adapter = mAdapter

//        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (!isLoading()) {
//                    if (!recyclerView.canScrollVertically(1)) {
//                        loadCharacters()
//                    }
//                }
//            }
//        })
    }

    private fun loadCharacters() {
        api = Retrofit.Builder()
                .baseUrl(MarvelApi.URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MarvelApi::class.java)

        api.listCharacters("1", "eb95459cac0b6177473decbeb608a839", "1ae5d2e78ffa4682eb728f01a6c0c4f5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    mCharacters = response.data!!.characters
                    mAdapter.setCharacters(mCharacters)
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
