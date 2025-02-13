package vttp.batch5.paf.movies.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vttp.batch5.paf.movies.services.MovieService;


@Component
public class Dataloader implements CommandLineRunner{

    @Value("${zip.file.path}")
    private static String zipName;

    @Autowired
    MovieService movieService;

  //TODO: Task 2

    @Override
    public void run(String... args) throws Exception {
        try {
            // movieService.readFile(zipName);
            String name = "movies_post_2010.zip";
            movieService.readFile(name);
        } catch (Exception e) {
            System.err.print(e);
        }
    }

  

}


