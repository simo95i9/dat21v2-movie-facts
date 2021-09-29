package moviefacts;

import moviefacts.models.MovieList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieFactsApplication {
    public final static MovieList movies = new MovieList();

    public static void main(String[] args) {
        movies.loadData();

        SpringApplication.run(MovieFactsApplication.class, args);
    }


}
