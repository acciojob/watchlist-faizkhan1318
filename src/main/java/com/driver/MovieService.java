package com.driver;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private MovieRepository movieRepository=new MovieRepository();
    public void addMovie(Movie movie) {
        movieRepository.addNewMovie(movie);

    }
    public void addDirectory(Director director) {
        movieRepository.addNewDirectory(director);
    }

    public void addMovieDirectorPair(String movie, String director) {
        Optional<Movie> movieOpt = movieRepository.getMovie(movie);
        Optional<Director> directorOpt = movieRepository.getDirector(director);
        if (movieOpt.isEmpty()){
            throw new RuntimeException("Movie Not Found Exception");
        } if(directorOpt.isEmpty()) {
            throw new RuntimeException("Director not found Exception");
        }
        Director directorObj=directorOpt.get();
        directorObj.setNumberOfMovies(directorObj.getNumberOfMovies()+1);
        movieRepository.addNewDirectory(directorObj);
        movieRepository.addMovieDirectorPair(movie, director);
    }

    public Movie getMovieByName(String name) throws Exception {
        Optional<Movie> movieOpt=movieRepository.getMovie(name);
        if(movieOpt.isPresent()){
            return movieOpt.get();
        }
        throw new Exception(name);
    }

    public Director getDirectorByName(String name) {
        Optional<Director> directorOpt=movieRepository.getDirector(name);
        if(directorOpt.isPresent()){
            return directorOpt.get();
        }
        throw new RuntimeException(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        return movieRepository.getMoviesByDirectorName(director);
    }

    public List<String> getAllmovies() {
        return movieRepository.getAllMovies();
    }

    public void deleteDirector(String director) {
        List<String> movies= getMoviesByDirectorName(director);
        movieRepository.deleteDirector(director);
        for(String mov:movies){
            movieRepository.deleteMovies(mov);
        }
    }

    public void deleteAllDirector() {
        List<String> directors=movieRepository.getAllDirector();
        for (String dir:directors){
            deleteDirector(dir);
        }
    }
}
