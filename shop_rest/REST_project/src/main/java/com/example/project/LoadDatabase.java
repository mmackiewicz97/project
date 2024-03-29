package com.example.project;
import com.example.project.model.Kategorie;
import com.example.project.repository.KategorieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(KategorieRepository repository) {

        return args -> {
//            log.info("Preloading " + repository.save(new Kategorie("Pierwsza kategoria")));
//            log.info("Preloading " + repository.save(new Kategorie("Druga kategoria")));
            log.info("Starting without adding new rows");
        };
    }

}
