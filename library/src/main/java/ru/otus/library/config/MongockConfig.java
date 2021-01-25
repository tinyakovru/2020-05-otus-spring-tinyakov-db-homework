package ru.otus.library.config;

import com.github.cloudyrock.mongock.*;
import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.library.config.MongoProps;


@Configuration
public class MongockConfig {
    private static final String CHANGELOGS_PACKAGE = "ru.otus.library.changelog";

    @Bean
    public SpringMongock mongock(MongoClient mongoClient, MongoProps props) {
        return new SpringMongockBuilder(mongoClient, props.getDatabase(), CHANGELOGS_PACKAGE)
                .build();
    }
}
