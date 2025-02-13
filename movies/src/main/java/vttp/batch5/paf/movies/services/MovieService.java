package vttp.batch5.paf.movies.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.json.data.JsonDataSource;
import net.sf.jasperreports.pdf.JRPdfExporter;
import net.sf.jasperreports.pdf.SimplePdfExporterConfiguration;
import net.sf.jasperreports.pdf.SimplePdfReportConfiguration;
import vttp.batch5.paf.movies.models.Director;
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
  // @Transactional
  public void readFile (String filePath) throws IOException, ParseException {
    ZipFile zipFile = new ZipFile(filePath);

    Enumeration<? extends ZipEntry> entries = zipFile.entries();

    while(entries.hasMoreElements()){
        ZipEntry entry = entries.nextElement();
        InputStream stream = zipFile.getInputStream(entry);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String line = "";
            List<Movie> documents = new ArrayList<>();

              while ((line = br.readLine()) != null) {
                  JsonReader jReader = Json.createReader(new StringReader(line));
                  JsonObject jObject = jReader.readObject();
                  Movie m = new Movie();
                  try {
                    m.setImdb_id(jObject.getString("imdb_id"));
                  } catch (Exception e) {
                    m.setImdb_id("");
                  }
                  try {
                    m.setVote_average(jObject.getInt("vote_average"));
                  } catch (Exception e) {
                    m.setVote_average(0);
                  }
                  try {
                    m.setVote_count(jObject.getInt("vote_count"));
                  } catch (Exception e) {
                    m.setVote_count(0);
                  }
                  String d = jObject.get("release_date").toString();
                  String dt = d.substring(1, 11);
                  // System.out.println(dt);
                  Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                  LocalDate nDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                  m.setRelease_date(nDate);
                  try {
                    m.setRevenue(jObject.getInt("revenue"));
                  } catch (Exception e) {
                    m.setRevenue(0);
                  }
                  try {
                    m.setBudget(jObject.getInt("budget"));
                  } catch (Exception e) {
                    m.setBudget(0);
                  }
                  try {
                    m.setRuntime(jObject.getInt("runtime"));
                  } catch (Exception e) {
                    m.setRuntime(0);
                  }
                  try {
                    m.setTitle(jObject.getString("title"));
                  } catch (Exception e) {
                    m.setTitle("");
                  }
                  try {
                    m.setDirector(jObject.getString("director"));
                  } catch (Exception e) {
                    m.setDirector("");
                  }
                  try {
                    m.setOverview(jObject.getString("overview"));
                  } catch (Exception e) {
                    m.setOverview("");
                  }
                  try {
                    m.setTagline(jObject.getString("tagline"));
                  } catch (Exception e) {
                    m.setTagline("");
                  }
                  try {
                    m.setGenres(jObject.getString("genres"));
                  } catch (Exception e) {
                    m.setGenres("");
                  }
                  try {
                    m.setImdb_rating(jObject.getInt("imdb_rating"));
                  } catch (Exception e) {
                    m.setImdb_rating(0);
                  }
                  try {
                    m.setImdb_votes(jObject.getInt("imdb_votes"));
                  } catch (Exception e) {
                    m.setImdb_votes(0);
                  }
                    // System.out.println(m.toString());
                    documents.add(m);
                  
              }
            br.close();
            
            for (int i = 0; i < documents.size(); i++) {
                Movie m = documents.get(i);

                LocalDate d1 = LocalDate.of(2018, 1, 1);

                LocalDate d = m.getRelease_date();
                
                if (d.isBefore(d1)) {
                  try {
                    documents.remove(i);
                  } catch (Exception e) {
                    System.out.println(e);
                  }
                    continue;
                }
                try {
                  mySQLMovieRepo.insertMovies(m);
                } catch (Exception e) {
                  continue;
                }
                
            }

            // List<Object[]> params = documents.stream()
            //     .map(li -> {
            //         Object[] rec = new Object[7];
            //         rec[0] = li.getImdb_id();
            //         rec[1] = li.getVote_average();
            //         rec[2] = li.getVote_count();
            //         rec[3] = li.getRelease_date();
            //         rec[4] = li.getRevenue();
            //         rec[5] = li.getBudget();
            //         rec[6] = li.getRuntime();

            //         return rec;
            //     }).toList();
                

            //     while (params.size() > 0) {
            //       for (int i = 0; i < 26; i++) {
            //         List<Object[]> batch = new ArrayList<>();
            //         batch.add(params.get(i));
            //         params.remove(i);
            //         System.out.println(batch);
            //       }
                  
            //       try {
            //         int[] added = mySQLMovieRepo.batchInsertMovies(params);
            //         System.out.print(added);
            //       } catch (Exception e) {
            //         Document d = new Document();
            //         d.put("ids", params.get(0));
            //         d.put("error", e.getMessage());
            //         d.put("timestamp", new Date().toInstant().toString());

            //         mongoMovieRepo.logError(d, "errors");
            //         System.err.print(e);
            //         throw new RuntimeException();
            //         }
                  
            //     }
                // System.out.print(">>>>>>>>hello");

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
                // System.out.println(d.toString());
                docs.add(d);
            }

            try {
                
                mongoMovieRepo.insertMovies(docs, "imdb");
                // Collection<Document> newDocs = mongoMovieRepo.batchInsertMovies(docs, "imdb");
                
            } catch (Exception e) {
                Document d = new Document();
                d.put("ids", d.get(0));
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
  public List<Director> getProlificDirectors() {
    List<Document> docs = mongoMovieRepo.getMongoDir();
    List<Director> dirs = new ArrayList<>();

    for (Document d: docs) {
      List<String> imdbids = d.getList("imdb_ids", String.class);
      Float revenue_tot = 0.00f;
      Float budget_tot = 0.00f;
      for (String s: imdbids) {
        Optional<Float> r = mySQLMovieRepo.getRevenue(s);
        if (r.isEmpty()) {
          continue;
        }
        Float re = r.get();
        revenue_tot = revenue_tot + re;

        Optional<Float> b = mySQLMovieRepo.getBudget(s);
        if (b.isEmpty()) {
          continue;
        }
        Float bu = b.get();
        budget_tot = budget_tot + bu;

        Director dir = new Director();
        dir.setDirector_name(d.getString("_id"));
        dir.setMovies_count(d.getInteger("count"));
        dir.setTotal_revenue(revenue_tot);
        dir.setTotal_budget(budget_tot);

        dirs.add(dir);
      }
      
    }
    dirs.remove(0);
    
    return dirs;
  }

  // TODO: Task 4
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public void generatePDFReport(String name, String batch, List<Director> list) throws JRException {
    JsonObjectBuilder jObjectB = Json.createObjectBuilder();
    jObjectB.add("name", name);
    jObjectB.add("batch", batch);
    JsonObject jsonObject = jObjectB.build();
    String jsonString = jsonObject.toString();

    JsonDataSource reportDS = new JsonDataSource(new ByteArrayInputStream(jsonString.getBytes()));

    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    list.stream().forEach(d -> arrayBuilder.add(
      Json.createObjectBuilder()
        .add("director", d.getDirector_name())
        .add("count", d.getMovies_count())
        .add("revenue", d.getTotal_revenue())
        .add("budget", d.getTotal_budget())
        .build()
    ));
    JsonArray jArray = arrayBuilder.build();
    String jsonString2 = jArray.toString();

    JsonDataSource directorsDS = new JsonDataSource(new ByteArrayInputStream(jsonString2.getBytes()));

    Map<String, Object> params = new HashMap<>();
    params.put("DIRECTOR_TABLE_DATASET", directorsDS);

    File jasperFile = new File("director_movies_report.jasper");
    JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(jasperFile.getPath());
    
    JasperPrint print = JasperFillManager.fillReport(report, params, reportDS);

    JRPdfExporter exporter = new JRPdfExporter();

    exporter.setExporterInput(new SimpleExporterInput(print));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("directorReport.pdf"));

    SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
    reportConfig.setSizePageToContent(true);
    reportConfig.setForceLineBreakPolicy(false);

    SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
    exportConfig.setMetadataAuthor("baeldung");
    exportConfig.setEncrypted(true);
    exportConfig.setAllowedPermissionsHint("PRINTING");
    
    exporter.setConfiguration(reportConfig);
    exporter.setConfiguration(exportConfig);
    exporter.exportReport();
  }

}
