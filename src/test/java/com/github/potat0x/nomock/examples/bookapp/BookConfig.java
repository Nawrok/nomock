package com.github.potat0x.nomock.examples.bookapp;

import com.github.potat0x.nomock.examples.repositories.CrudBookRepository;
import com.github.potat0x.nomock.examples.repositories.InMemoryCrudBookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("in-memory-repository")
public class BookConfig {

    @Bean
    public CrudBookRepository bookRepository() {
        return new InMemoryCrudBookRepository();
    }
}
