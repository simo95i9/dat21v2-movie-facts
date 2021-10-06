package moviefacts;

import moviefacts.models.MovieList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Map;

@SpringBootApplication
public class MovieFactsApplication {
    public final static MovieList movies = new MovieList();
    public final static Map<String, String> env = System.getenv();

    public static void main(String[] args) {
        movies.loadData();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }

        SpringApplication.run(MovieFactsApplication.class, args);
    }


}
