package vttp.batch5.paf.movies.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import vttp.batch5.paf.movies.models.Movie;
import vttp.batch5.paf.movies.repositories.MongoMovieRepository;
import vttp.batch5.paf.movies.repositories.MySQLMovieRepository;

@Service
public class MovieService {

    @Autowired
    private MySQLMovieRepository mySQLMovieRepo;

    @Autowired
    MongoMovieRepository mongoMovieRepo;

  // TODO: Task 2
  @Transactional
  public void readFile (String filePath) throws IOException {
    ZipFile zipFile = new ZipFile(filePath);

    Enumeration<? extends ZipEntry> entries = zipFile.entries();

    while(entries.hasMoreElements()){
        ZipEntry entry = entries.nextElement();
        InputStream stream = zipFile.getInputStream(entry);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            JsonReader jsonReader = Json.createReader(br);
            JsonArray arr = jsonReader.readArray();
            String jsonContent = arr.toString();
            br.close();

            ObjectMapper mapper = new ObjectMapper();
            List<Movie> documents = mapper.readValue(jsonContent, new TypeReference<List<Movie>>() {});
            
            for (int i = 0; i < documents.size(); i++) {
                Movie m = documents.get(i);

                LocalDate d1 = LocalDate.of(2018, 1, 1);

                LocalDate d = m.getRelease_date();
                if (d.isBefore(d1)) {
                    documents.remove(i);
                    continue;
                }
            }

            List<Object[]> params = documents.stream()
                .map(li -> {
                    Object[] rec = new Object[7];
                    rec[0] = li.getImdb_id();
                    rec[1] = li.getVote_average();
                    rec[2] = li.getVote_count();
                    rec[3] = li.getRelease_date();
                    rec[4] = li.getRevenue();
                    rec[5] = li.getBudget();
                    rec[6] = li.getRuntime();

                    return rec;
                }).toList();

            try {
                int[] added = mySQLMovieRepo.batchInsertMovies(params);
            } catch (Exception e) {
                Document d = new Document();
                d.put("ids", params.get(0));
                d.put("error", e.getMessage());
                d.put("timestamp", new Date().toInstant().toString());

                mongoMovieRepo.logError(d, "errors");
                throw new RuntimeException();
            }
            

            List<Document> docs = new ArrayList<>();
            for (int i = 0; i < documents.size(); i++) {
                Movie m = documents.get(i);

                Document d = new Document();
                d.put("_id", m.getImdb_id());
                d.put("title", m.getTitle());
                d.put("directors", m.getDirector());
                d.put("overviews", m.getOverview());
                d.put("tagline", m.getTagline());
                d.put("genres", m.getGenres());
                d.put("imdb_rating", m.getImdb_rating());
                d.put("imdb_votes", m.getImdb_votes());

                docs.add(d);
            }

            try {
                Collection<Document> newDocs = mongoMovieRepo.batchInsertMovies(docs, "imdb");
            } catch (Exception e) {
                Document d = new Document();
                d.put("ids", params.get(0));
                d.put("error", e.getMessage());
                d.put("timestamp", new Date().toInstant().toString());

                mongoMovieRepo.logError(d, "errors");
                throw new RuntimeException();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

  }

  // TODO: Task 3
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public void getProlificDirectors() {
  }


  // TODO: Task 4
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public void generatePDFReport() {

  }

}
