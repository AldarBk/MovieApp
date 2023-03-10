package com.example.movieapp.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.R
import com.example.movieapp.data.MovieDetails
import com.example.movieapp.model.apis.ApiInterface
import com.example.movieapp.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        initViews()
        initObserver()
    }

    private fun initObserver() {
        mViewModel.apply {
            movieDetails.observe(this@MoviesDetailsActivity){
                setMovieInformation()
            }
        }
    }


    private fun setMovieInformation() {
        val id = intent.getIntExtra("id", 0)
        val apiInterface = id?.let {
            ApiInterface.create().getMovieDetails(it, "02b113b496621e5a49428c55c55a3ccc")
        }
        apiInterface?.enqueue(/* callback = */ object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>?) {
                title.text = response?.body()?.title
                releaseData.text = response?.body()?.release_date.toString()
                score.text = response?.body()?.vote_average.toString()
                overview.text = response?.body()?.overview
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500" + response?.body()?.backdrop_path)
                    .into(banner)
            }

            override fun onFailure(call: Call<MovieDetails>?, t: Throwable?) {
            }
        })

    }

    private fun initViews() {
        title = findViewById(R.id.movies_details_title)
        releaseData = findViewById(R.id.movies_details_date)
        score = findViewById(R.id.movie_details_score)
        overview = findViewById(R.id.movies_details_body_overview_details)
        banner = findViewById(R.id.movies_details_image_banner)
    }
}