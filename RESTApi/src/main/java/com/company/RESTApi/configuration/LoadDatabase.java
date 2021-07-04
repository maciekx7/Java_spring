package com.company.RESTApi.configuration;

import com.company.RESTApi.models.GPSInfo;
import com.company.RESTApi.models.GPSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class
 */
@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * Default GPSInfo data is initialized
     * @param repository GPSRepository which represents repository of GPSInfo objects
     */
    @Bean
    CommandLineRunner initDatabase(GPSRepository repository) {
        return args -> {
            log.info("Creating " + repository.save(new GPSInfo(123,2222,3333)));
            log.info("Creating " + repository.save(new GPSInfo(122, 444,3232)));
            log.info("Creating " + repository.save(new GPSInfo(122, 55,3232)));
        };
    }
}
