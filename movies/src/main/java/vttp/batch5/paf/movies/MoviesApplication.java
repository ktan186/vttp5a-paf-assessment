package vttp.batch5.paf.movies;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.batch5.paf.movies.bootstrap.Dataloader;

@SpringBootApplication
public class MoviesApplication {

	@Value("${zip.file.path}")
   private static String zipName;
   
	@Autowired
	private static Dataloader dataloader;

		public static void main(String[] args) throws IOException {
			// dataloader.readFile(zipName);
		

		SpringApplication.run(MoviesApplication.class, args);
	}


}
