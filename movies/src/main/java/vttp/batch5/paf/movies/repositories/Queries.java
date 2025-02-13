package vttp.batch5.paf.movies.repositories;

public class Queries {
    
    public static final String SQL_INSERT_SQL = 
        """ 
        insert imdb (imdb_id, vote_average, vote_count, release_date, revenue, budget, runtime)
        values (?, ?, ?, ?, ?, ?, ?)
        """;

}
