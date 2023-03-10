package com.example.movieapp.model.repository

import com.example.movieapp.data.MovieDetails
import com.example.movieapp.data.Movies
import retrofit2.Call

interface MoviesDBRepository {

    /**
     * Возвращает список популярных фильмов
     */
    fun getMovie(): Call<Movies>

    /**
     * Получает данные о фильме
     * id - id выбранного фильма
     */
    fun getMovieDetails(id: Int): Call<MovieDetails>
}
