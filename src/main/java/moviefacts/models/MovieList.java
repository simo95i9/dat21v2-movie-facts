package moviefacts.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class MovieList {
    private final List<Movie> movies;
    private final File movieDataFile = new File("src/main/resources/movies.tsv");

    public MovieList() {
        this.movies = new ArrayList<>();
    }

    public void loadData() {
        try (Scanner scanner = new Scanner( movieDataFile ) ) {
            scanner.nextLine();
            while ( scanner.hasNextLine() ) {
                String line = scanner.nextLine();
                String[] fields = line.split("\t");
                assert fields.length == 6 : "fields.length assertion failed, invalid line in movie data:\n" + line;

                Year year = Year.of(Integer.parseInt(fields[0].replaceAll("\"", "")));
                Integer length = Integer.parseInt(fields[1].replaceAll("\"", ""));
                String title = fields[2].replaceAll("\"", "");
                String subject = fields[3].replaceAll("\"", "");
                Integer popularity = Integer.parseInt(fields[4].replaceAll("\"", ""));
                Boolean awards = fields[5].replaceAll("\"", "").equalsIgnoreCase("Yes");

                movies.add(new Movie(year, length, title, subject, popularity, awards));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println( "Couldn't open movie data file" );
        }
    }

    public <T extends Comparable<T>> List<Movie> sorted(Function<Movie, T> keyExtractor) {
        return this.movies.stream()
                .sorted( Comparator.comparing(keyExtractor) )
                .toList();
    }

    public List<Movie> filter(Predicate<Movie> predicate ) {
        return this.movies.stream()
                .filter( predicate )
                .toList();
    }

    public Optional<Movie> get(Integer index) {
        try {
            return Optional.of( movies.get(index) );
        } catch (IndexOutOfBoundsException e ) {
            return Optional.empty();
        }
    }

    public Integer length() {
        return this.movies.size();
    }
}
