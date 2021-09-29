package moviefacts.models;

import java.time.Year;

public record Movie(Year year, Integer length, String title, String subject, Integer popularity, Boolean awards)  {
    @Override
    public String toString() {
        return "Movie{" +
                "year=" + year +
                ", length=" + length +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", popularity=" + popularity +
                ", awards=" + awards +
                '}';
    }
}
