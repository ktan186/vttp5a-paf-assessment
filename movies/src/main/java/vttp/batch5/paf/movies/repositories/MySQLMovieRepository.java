package vttp.batch5.paf.movies.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.batch5.paf.movies.models.Movie;

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

  public int insertMovies(Movie m) {
    int added = template.update(Queries.SQL_INSERT_SQL, m.getImdb_id(), m.getVote_average(), m.getVote_count(), m.getRelease_date(), m.getRevenue(), m.getBudget(), m.getRuntime());
    return added;
  }
  
  // TODO: Task 3
  public Optional<Float> getRevenue(String id) {
    SqlRowSet rs = template.queryForRowSet(Queries.SQL_GET_INFO, id);
    if (!rs.next()) {
      return Optional.empty();
    }
    return Optional.of(rs.getFloat("revenue"));
  }

  public Optional<Float> getBudget(String id) {
    SqlRowSet rs = template.queryForRowSet(Queries.SQL_GET_INFO, id);
    if (!rs.next()) {
      return Optional.empty();
    }
    return Optional.of(rs.getFloat("budget"));
  }

}
