package vttp.batch5.paf.movies.repositories;

public class Queries {
    
    public static final String SQL_INSERT_SQL = 
        """ 
        insert imdb (imdb_id, vote_average, vote_count, release_date, revenue, budget, runtime)
        values (?, ?, ?, ?, ?, ?, ?)
        """;

    public static final String SQL_GET_INFO= 
        """ 
        select * from imdb 
            where imdb_id = ?
        """;

}
