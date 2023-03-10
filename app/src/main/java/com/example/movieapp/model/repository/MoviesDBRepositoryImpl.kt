package com.example.movieapp.model.repository

import com.example.movieapp.Constants
import com.example.movieapp.data.MovieDetails
import com.example.movieapp.data.Movies
import com.example.movieapp.model.apis.ApiInterface
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository {
    private val apiInterface = ApiInterface.create()
    override fun getMovie(): Call<Movies> {
        return apiInterface.getMovies(Constants.API_KEY)
    }

    override fun getMovieDetails(id: Int): Call<MovieDetails> {
        return apiInterface.getMovieDetails(id, Constants.API_KEY)
    }
}