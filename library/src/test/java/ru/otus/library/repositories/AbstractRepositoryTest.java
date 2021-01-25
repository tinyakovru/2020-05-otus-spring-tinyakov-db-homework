package ru.otus.library.repositories;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
//import ru.otus.example.mongodbdemo.utils.RawResultPrinterImpl;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.library.config", "ru.otus.library.repositories"})
//@Import(RawResultPrinterImpl.class)
public abstract class AbstractRepositoryTest {
}
