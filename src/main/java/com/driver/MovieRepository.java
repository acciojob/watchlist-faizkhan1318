package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    private Map<String,Movie> movieData=new HashMap<>();
    private Map<String,Director> directoryData=new HashMap<>();

    private Map<String, ArrayList<String>> movieDirectorpair=new HashMap<>();
    public void addNewMovie(Movie movie) {
        movieData.put(movie.getName(),movie);
    }

    public void addNewDirectory(Director director) {
        directoryData.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movie, String director) {

        ArrayList<String> movies=movieDirectorpair.getOrDefault(director,new ArrayList<String>());
        movies.add(movie);
        movieDirectorpair.put(director,movies);
    }

    public Optional<Movie> getMovie(String movie) {
        if(movieData.containsKey(movie)){
            return Optional.of(movieData.get(movie));
        }
        return Optional.empty();
    }

    public Optional<Director> getDirector(String director) {
        if(directoryData.containsKey(director)){
            return Optional.of(directoryData.get(director));
        }
        return Optional.empty();
    }

    public List<String> getMoviesByDirectorName(String director) {
        return movieDirectorpair.getOrDefault(director,new ArrayList<>());

    }

    public List<String> getAllMovies() {
        return new ArrayList<>(movieData.keySet());
    }
    public void deleteDirector(String director) {
        directoryData.remove(director);
        movieDirectorpair.remove(director);
    }
    public void deleteMovies(String mov) {
        movieData.remove(mov);
    }

    public List<String> getAllDirector() {
        return new ArrayList<>(directoryData.keySet());
    }
}
