package at.ac.fhcampuswien.fhmdb.utils;

import at.ac.fhcampuswien.fhmdb.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieUtils {

    /**
     * ToDo: Refactor for API by Jakob
     */
    public static List<Movie> search(String input, List<Movie> movieList) {
        return movieList.stream()
                .filter(movie -> isMovieMatchesSearchQuery(movie, input))
                .collect(Collectors.toList());
    }

    /**
     * ToDo: Refactor for API by Jakob
     */
    public static List<Movie> filter(Genre selectedGenre, List<Movie> movieList, String searchQuery) {
        if (movieList == null || movieList.isEmpty()) {
            return new ArrayList<>();
        }

        List<Movie> filteredList = selectedGenre == null || selectedGenre == Genre.ALL
                ? movieList
                : movieList.stream().filter(movie -> movie.getGenres().contains(selectedGenre)).toList();

        if (!searchQuery.isEmpty()) {
            filteredList = search(searchQuery, filteredList);
        }

        return filteredList;
    }
    /**
     * ToDo: Refactor for API by Jakob
     */
    protected static boolean isMovieMatchesSearchQuery(Movie movie, String searchQuery) {
        return movie.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                movie.getDescription().toLowerCase().contains(searchQuery.toLowerCase());
    }

    /**
     * ToDo: Refactor for API by Jakob
     */
    public static List<Movie> sort(String mode, List<Movie> movieList){
        if(movieList.isEmpty()){
            return movieList;
        }

        if ("descending".equals(mode)) {
            movieList.sort(Comparator.comparing(Movie::getTitle).reversed());
        } else {
            movieList.sort(Comparator.comparing(Movie::getTitle));
        }

        return movieList;
    }

    //ToDo API handling by Andi

    //ToDo @Sergiu
    public static String getMostPopularActor(List<Movie> movies){
        //TODO implement
        return "";
    }

    //ToDo @Sergiu
    public static int getLongestMovieTitle(List<Movie> movies){
        //TODO implement
        return 0;
    }

    //ToDo @Sergiu
    public static long countMoviesFrom(List<Movie> movies, String director)
    {
        //TODO implement
        return 0;
    }

    //ToDo @Sergiu
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int
            endYear){
        //TODO implement

        return null;
    }

    /**
     * Parses a JSON string from Response to a list of movies
     * @param jsonString
     * @return
     */
    public static List<Movie> parseMovies(String jsonString) {
        Gson gson = new Gson();
        Type movieListType = new TypeToken<List<Movie>>(){}.getType();
        return gson.fromJson(jsonString, movieListType);
    }

}