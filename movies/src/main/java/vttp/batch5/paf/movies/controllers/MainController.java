package vttp.batch5.paf.movies.controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.batch5.paf.movies.models.Director;
import vttp.batch5.paf.movies.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import net.sf.jasperreports.engine.JRException;


@Controller
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

  // TODO: Task 3
   @Autowired
   private MovieService movieService;

   @GetMapping("/summary")
   @ResponseBody
   public ResponseEntity<String> getDirectors(@RequestParam Integer count) {
      List<Director> dirs = movieService.getProlificDirectors();
      List<Director> pf = new ArrayList<>();
      for (int i = 0; i < count; i++) {
        Director d = dirs.get(i);
        pf.add(d);
      }
      JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
      pf.stream().forEach(p -> arrBuilder.add(
        Json.createObjectBuilder()
          .add("director_name", p.getDirector_name())
          .add("movies_count", p.getMovies_count())
          .add("total_revenue", p.getTotal_revenue())
          .add("total_budget", p.getTotal_budget())
          .build()
      ));
      return ResponseEntity.ok(arrBuilder.build().toString());
   }
  
  //  @Value("${task4.name}")
  //  private static String name;

   String name = "Tan Kang Hui";

  //  @Value("${task4.batch}")
  //  private static String batch;

  String batch = "A";

  // TODO: Task 4
   @GetMapping("/summary/pdf")
   public ResponseEntity<String> getPdf(@RequestParam Integer count) throws JRException {
    List<Director> dirs = movieService.getProlificDirectors();
    List<Director> pf = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Director d = dirs.get(i);
      pf.add(d);
    }
    movieService.generatePDFReport(name, batch, pf);
    return ResponseEntity.ok().body(null);
   }
   

}
