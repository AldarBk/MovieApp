package com.example.movieapp.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.R
import com.example.movieapp.data.MovieDetails
import com.example.movieapp.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso

class MoviesDetailsActivity : AppCompatActivity() {

    private val mViewModel: MoviesViewModel = MoviesViewModel()

    private lateinit var title: TextView
    private lateinit var releaseData: TextView
    private lateinit var score: TextView
    private lateinit var overview: TextView
    private lateinit var banner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)

        val id = intent.getIntExtra("id", 0)

        initViews()
        initObserver()
        mViewModel.getMovieDetails(id)
    }

    private fun initObserver() {
        mViewModel.apply {
            movieDetails.observe(this@MoviesDetailsActivity) {
                setMovieInformation(it)
            }
        }
    }


    private fun setMovieInformation(movieDetails: MovieDetails) {
        title.text = movieDetails.title
        releaseData.text = movieDetails.release_date
        score.text = movieDetails.vote_average.toString()
        overview.text = movieDetails.overview

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movieDetails.backdrop_path)
            .into(banner)
    }


    private fun initViews() {
        title = findViewById(R.id.movies_details_title)
        releaseData = findViewById(R.id.movies_details_date)
        score = findViewById(R.id.movie_details_score)
        overview = findViewById(R.id.movies_details_body_overview_details)
        banner = findViewById(R.id.movies_details_image_banner)
    }
}