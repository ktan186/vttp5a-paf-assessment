package vttp.batch5.paf.movies.models;

import java.time.LocalDate;

public class Movie {
    private String title;
    private Integer vote_average;
    private Integer vote_count;
    private String status;
    private LocalDate release_date;
    private Long revenue;
    private Integer runtime;
    private Long budget;
    private String imdb_id;
    private String original_language;
    private String overview;
    private Integer popularity;
    private String tagline;
    private String genres;
    private String spoken_languages;
    private String casts;
    private String director;
    private Integer imdb_rating;
    private Long imdb_votes;
    private String poster_path;


    public Movie() {
    }
    
    public Movie(String title, Integer vote_average, Integer vote_count, String status, LocalDate release_date,
            Long revenue, Integer runtime, Long budget, String imdb_id, String original_language, String overview,
            Integer popularity, String tagline, String genres, String spoken_languages, String casts, String director,
            Integer imdb_rating, Long imdb_votes, String poster_path) {
        this.title = title;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.status = status;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.budget = budget;
        this.imdb_id = imdb_id;
        this.original_language = original_language;
        this.overview = overview;
        this.popularity = popularity;
        this.tagline = tagline;
        this.genres = genres;
        this.spoken_languages = spoken_languages;
        this.casts = casts;
        this.director = director;
        this.imdb_rating = imdb_rating;
        this.imdb_votes = imdb_votes;
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getVote_average() {
        return vote_average;
    }
    public void setVote_average(Integer vote_average) {
        this.vote_average = vote_average;
    }
    public Integer getVote_count() {
        return vote_count;
    }
    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Long getRevenue() {
        return revenue;
    }
    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }
    public Integer getRuntime() {
        return runtime;
    }
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }
    public Long getBudget() {
        return budget;
    }
    public void setBudget(Long budget) {
        this.budget = budget;
    }
    public String getImdb_id() {
        return imdb_id;
    }
    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }
    public String getOriginal_language() {
        return original_language;
    }
    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public Integer getPopularity() {
        return popularity;
    }
    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
    public String getTagline() {
        return tagline;
    }
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
    public String getGenres() {
        return genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public String getSpoken_languages() {
        return spoken_languages;
    }
    public void setSpoken_languages(String spoken_languages) {
        this.spoken_languages = spoken_languages;
    }
    public String getCasts() {
        return casts;
    }
    public void setCasts(String casts) {
        this.casts = casts;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public Integer getImdb_rating() {
        return imdb_rating;
    }
    public void setImdb_rating(Integer imdb_rating) {
        this.imdb_rating = imdb_rating;
    }
    public Long getImdb_votes() {
        return imdb_votes;
    }
    public void setImdb_votes(Long imdb_votes) {
        this.imdb_votes = imdb_votes;
    }
    public String getPoster_path() {
        return poster_path;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "Movie [title=" + title + ", vote_average=" + vote_average + ", vote_count=" + vote_count + ", status="
                + status + ", release_date=" + release_date + ", revenue=" + revenue + ", runtime=" + runtime
                + ", budget=" + budget + ", imdb_id=" + imdb_id + ", original_language=" + original_language
                + ", overview=" + overview + ", popularity=" + popularity + ", tagline=" + tagline + ", genres="
                + genres + ", spoken_languages=" + spoken_languages + ", casts=" + casts + ", director=" + director
                + ", imdb_rating=" + imdb_rating + ", imdb_votes=" + imdb_votes + ", poster_path=" + poster_path + "]";
    }

    
    
}
