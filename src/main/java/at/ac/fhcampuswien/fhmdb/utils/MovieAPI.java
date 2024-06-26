package at.ac.fhcampuswien.fhmdb.utils;
import at.ac.fhcampuswien.fhmdb.Genre;
import okhttp3.*;

public class MovieAPI {
    public static final String API_URL = "https://prog2.fh-campuswien.ac.at";

    //Todo Exception handling, forward
    public static String getAllMovies(String api_url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(api_url + "/movies")
                .header("User-Agent", "http.agent")
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                return response.body().string();
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        return "";
    }

    //Todo Exception handling, forward (own class movie exception class)

    /**
     *  retrofit library - https://square.github.io/retrofit/ and https://swagger.io/tools/swagger-codegen/
     */
    public static String getMoviesByQueries(String api_url, String query, Genre genre, int releaseYear, double ratingFrom) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = HttpUrl.parse(api_url + "/movies");
        HttpUrl.Builder urlBuilder;

        if (httpUrl != null) {
            urlBuilder = httpUrl.newBuilder();
            if (query != null) {
                urlBuilder.addQueryParameter("query", query);
            }
            if (genre != null) {
                urlBuilder.addQueryParameter("genre", genre.name());
            }
            if (releaseYear > 0) {
                urlBuilder.addQueryParameter("releaseYear", String.valueOf(releaseYear));
            }
            if (ratingFrom > 0) {
                urlBuilder.addQueryParameter("ratingFrom", String.valueOf(ratingFrom));
            }
        } else {
            throw new IllegalArgumentException("URL is not valid");
        }

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "http.agent")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                return response.body().string();
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        return "";
    }
}