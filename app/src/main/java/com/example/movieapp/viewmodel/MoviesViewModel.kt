package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.repository.MoviesDBRepository
import com.example.movieapp.model.repository.MoviesDBRepositoryImpl
import com.example.movieapp.data.MovieDetails
import com.example.movieapp.data.Movies
import com.example.movieapp.data.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel {
    private val _movies = MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>> = _movies


    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: MutableLiveData<MovieDetails> = _movieDetails

    private val mMovieRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovie() {
        val responce = mMovieRepository.getMovie()
        responce.enqueue(/* callback = */ object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                _movies.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {}
        })
    }

    fun getMovieDetails(id: Int){
        val responce = mMovieRepository.getMovieDetails(id)
        responce.enqueue(object : Callback<MovieDetails>{
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                _movieDetails.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
            }
        })
    }
}