package com.driver;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    MovieRepository movieRepository = new MovieRepository();
    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }
    public void addDirector(Director director) {
        movieRepository.addDirector(director);
    }
    public void addMovieDirectorPair(String movieName, String directorName) throws RuntimeException {
        Optional<Movie> movieOpt = movieRepository.getByMovieName(movieName);
        Optional<Director> directorOpt = movieRepository.getByDirectorNAme(directorName);

        if(movieOpt.isEmpty()){
            throw new RuntimeException("Movie Not Found");
        }
        if(directorOpt.isEmpty()){
            throw new RuntimeException("Director Not Found");
        }
        Director director = directorOpt.get();
        director.setNumberOfMovies(director.getNumberOfMovies()+1);
        movieRepository.addDirector(director);
        movieRepository.addMovieDirectorPair(movieName, directorName);

    }
    public Movie getMovieByName(String name) throws RuntimeException {
        Optional<Movie> movieOpt=movieRepository.getByMovieName(name);
        if(movieOpt.isPresent()){
            return movieOpt.get();
        }
       throw new RuntimeException(name);
    }

    public Director getDirectorByName(String directorName) throws RuntimeException {
        Optional<Director> directorOpt=movieRepository.getByDirectorNAme(directorName);
        if(directorOpt.isPresent()){
            return directorOpt.get();
        }
        throw new RuntimeException(directorName);
    }

    public List<String> movieListByDirector(String name) {
        List<String> movies = movieRepository.movieListByDirector(name);
        return movies;
    }

    public List<String> allMovies() {
        List<String> movies = movieRepository.allMovies();
        return movies;
    }

    public void deleteDirectorByName(String name) {
        List<String> moviesList = movieRepository.movieListByDirector(name);
        movieRepository.delete(name);
        for(String movies : moviesList){
            movieRepository.removeMovies(movies);
        }
    }

    public void deleteAllDirectors() {
        List<String> director = movieRepository.getAllDirectors();
        for(String dir : director){
            movieRepository.delete(dir);
        }

    }
}
