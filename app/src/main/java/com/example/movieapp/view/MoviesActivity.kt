package com.example.movieapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.view.adapter.CustomAdapter
import com.example.movieapp.viewmodel.MoviesViewModel

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
