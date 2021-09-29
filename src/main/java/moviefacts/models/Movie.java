package moviefacts.models;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Year;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Movie(Year year, Integer length, String title, String subject, Integer popularity, Boolean awards)  {

    public Optional<URL> apiURL() {
        try {
            System.out.println("YOU NEED TO SUPPLY YOUR OWN API KEY AND UNCOMMENT THE CODE");

            URL tmdb_movie_search = new DefaultUriBuilderFactory().builder()
                    .scheme("https")
                    .host("api.themoviedb.org")
                    .path("/3")
                    .path("/search")
                    .path("/movie")
                    //.queryParam("api_key", "INCLUDE YOUR OWN API KEY HERE")
                    .queryParam("query", this.title.replaceAll(" ", "+"))
                    .queryParam("year", this.year)
                    .build()
                    .toURL();

            return Optional.of( tmdb_movie_search );
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<URL> imageURL() {
        if ( this.apiURL().isEmpty() ) {
            return Optional.empty();
        }

        try (BufferedReader br = new BufferedReader( new InputStreamReader(this.apiURL().get().openConnection().getInputStream()) ) ) {
            String apiResponse = br.readLine();
            Pattern pattern = Pattern.compile("\"poster_path\":\"(.+?)\",");
            Matcher match = pattern.matcher(apiResponse);
            if ( match.find() ) {
                return Optional.of(
                        new DefaultUriBuilderFactory().builder()
                                .scheme("https")
                                .host("www.themoviedb.org")
                                .path("/t/p/w500")
                                .path( match.group(1) )
                                .build()
                                .toURL()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        Optional<URL> imageURL = this.imageURL();
        String imageURLString = imageURL.isPresent() ? imageURL.get().toString() : "";

        return "Movie{" +
                "year=" + year +
                ", length=" + length +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", popularity=" + popularity +
                ", awards=" + awards +
                ", poster='" + imageURLString + '\'' +
                '}';
    }
}
