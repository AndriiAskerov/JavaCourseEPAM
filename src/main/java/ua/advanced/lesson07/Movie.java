package ua.advanced.lesson07;

import java.sql.Date;

public class Movie {
    private int id;
    private String title;
    private Date releaseDate;
    private String country;

    public Movie(int id, String title, Date releaseDate, String country) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.country = country;
    }

    // get() & set() methods:
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    // additional methods:
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", release_date=" + releaseDate +
                ", country='" + country + '\'' +
                '}';
    }
}
