package com.example.movieapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.*
import com.example.movieapp.data.ItemsViewModel
import com.example.movieapp.data.Movies
import com.example.movieapp.model.apis.ApiInterface
import com.example.movieapp.view.adapter.CustomAdapter
import com.example.movieapp.viewmodel.MoviesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity(),CustomAdapter.ItemClickListener {
    private val mViewModel: MoviesViewModel = MoviesViewModel()
    private val mMoviesViewModel: MoviesViewModel = MoviesViewModel()

    private lateinit var mMoviesRecycler: RecyclerView
    private lateinit var mMoviesAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        initViews()
        initObservers()
        mViewModel.getMovie()

        // getting the recyclerview by its id
//        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
//
//        // this creates a vertical layout Manager
//        recyclerview.layoutManager = GridLayoutManager(this, 2)
//
//        // ArrayList of class ItemsViewModel
//        val data = ArrayList<ItemsViewModel>()
//
//        // This loop will create 20 Views containing
//        // the image with the count of view
//        for (i in 1..20) {
//            data.add(
//                ItemsViewModel(
//                    com.google.android.gms.base.R.drawable.common_full_open_on_phone,
//                    "Item $i"
//                )
//            )
//        }


//        val apiInterface = ApiInterface.create().getMovies("02b113b496621e5a49428c55c55a3ccc")
//
//        //apiInterface.enqueue( Callback<List<Movie>>())
//        apiInterface.enqueue(/* callback = */ object : Callback<Movies>,
//            CustomAdapter.ItemClickListener {
//            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
//                val adapter = CustomAdapter(response?.body()?.results, this)
//                recyclerview.adapter = adapter
//            }
//            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
//            }
//
//            override fun onItemClick(id: Int) {
//                val intent = Intent(this@MoviesActivity, MoviesDetailsActivity::class.java)
//                intent.putExtra("id", id)
//                startActivity(intent)
//            }
//        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this@MoviesActivity, MoviesDetailsActivity::class.java)
        intent.putExtra("id",position)
        startActivity(intent)
    }



    private fun initViews(){
        mMoviesRecycler = findViewById(R.id.recyclerview)
        mMoviesRecycler.layoutManager = GridLayoutManager(this@MoviesActivity,2)
    }

    private fun initObservers() {
        mViewModel.apply {
            movies.observe(this@MoviesActivity){
                mMoviesAdapter = CustomAdapter(it,this@MoviesActivity)
                mMoviesRecycler.adapter = mMoviesAdapter
            }
        }
    }



}
