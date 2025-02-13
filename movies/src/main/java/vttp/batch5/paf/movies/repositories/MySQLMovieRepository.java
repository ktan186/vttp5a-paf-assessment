package vttp.batch5.paf.movies.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MySQLMovieRepository {

  @Autowired
  private JdbcTemplate template;

  // TODO: Task 2.3
  // You can add any number of parameters and return any type from the method
  public int[] batchInsertMovies(List<Object[]> params) {
    int[] added = template.batchUpdate(Queries.SQL_INSERT_SQL, params);
    return added;
  }
  
  // TODO: Task 3


}
