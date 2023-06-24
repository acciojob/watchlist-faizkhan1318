package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    Map<String, Movie> movieMap = new HashMap<>();
    Map<String, Director> directorMap = new HashMap<>();
    Map<String, List<String>> directorMovieMap = new HashMap<>();
    Map<String, String> movieDirectorMap = new HashMap<>();
    public void addMovie(Movie movie) {
        movieMap.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directorMap.put(director.getName(), director);
    }

    public Optional<Movie> getByMovieName(String movieName) {
        if(movieMap.containsKey(movieName)){
            return Optional.of(movieMap.get(movieName));
        }
        return Optional.empty();
    }
    public Optional<Director> getByDirectorNAme(String directorName) {
        if(directorMap.containsKey(directorName)){
            return Optional.of(directorMap.get(directorName));
        }
        return Optional.empty();
    }
    public void addMovieDirectorPair(String movieName, String directorName) {
        List<String> movieList = directorMovieMap.getOrDefault(directorName, new ArrayList<>());
        movieList.add(movieName);
        directorMovieMap.put(directorName, movieList);
        movieDirectorMap.put(movieName, directorName);
    }
}
