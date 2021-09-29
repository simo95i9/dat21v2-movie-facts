package moviefacts.services;

import moviefacts.MovieFactsApplication;
import moviefacts.models.Movie;

import java.util.List;
import java.util.Optional;

public class MovieFactsServices {
    public static String index() {
        return
        """
        <p>
        Hello there,
        <br>
        Welcome to the Movie Facts Site!
        </p>
        <p>
        Try to visit these different endpoints to see the data.
        <ul>
            <li><a href="/get-first">/get-first</a></li>
            <li><a href="/get-random">/get-random</a></li>
            <li><a href="/get-ten-sort-by-popularity">/get-ten-sort-by-popularity</a></li>
            <li><a href="/how-many-won-an-award">/how-many-won-an-award</a></li>
        </ul>
        </p>
        """;
    }

    public static String getFirst() {
        Optional<Movie> optionalMovie = MovieFactsApplication.movies.get(0);
        if ( optionalMovie.isPresent() ) {
            return """
                    <p>Here is the first movie from the datastore</p>
                    
                    <p>%s</p>
                   """.formatted( optionalMovie.get() );
        } else {
            return """
                   Couldn't find the first movie. Maybe the datastore is empty?
                   """;
        }
    }


    public static String getRandom() {
        Integer randomIndex = (int) (MovieFactsApplication.movies.length() * Math.random());
        Optional<Movie> optionalMovie = MovieFactsApplication.movies.get(randomIndex);
        if ( optionalMovie.isPresent() ) {
            return """
                    <p>Here is a random movie from the datastore</p>
                    
                    <p>%s</p>
                   """.formatted( optionalMovie.get() );
        } else {
            return """
                   Couldn't find the first movie. Maybe the datastore is empty?
                   """;
        }
    }

    public static String getTenSortByPopularity() {
        List<Movie> sortedMovies = MovieFactsApplication.movies.sorted( Movie::popularity ) ;
        List<Movie> tenSortedMovies = sortedMovies.subList(0, Integer.min(10, sortedMovies.size()));

        StringBuilder sb = new StringBuilder();
        tenSortedMovies.forEach( movie -> sb.append("<li>").append(movie).append("</li>\n") );

        return """
                <p>Here are ten movies sorted by popularity</p>
                
                <p><ul>
                %s
                </ul></p>
                """.formatted(sb.toString());
    }

    public static String howManyWonAnAward() {
        List<Movie> filteredMovies = MovieFactsApplication.movies.filter( Movie::awards ) ;

        StringBuilder sb = new StringBuilder();
        filteredMovies.forEach( movie -> sb.append("<li>").append(movie).append("</li>\n") );

        return """
                <p>Here are all the movies that have won an award. There's %,d movies in total</p>
                
                <p><ul>
                %s
                </ul></p>
                """.formatted(filteredMovies.size(), sb.toString());
    }
}
