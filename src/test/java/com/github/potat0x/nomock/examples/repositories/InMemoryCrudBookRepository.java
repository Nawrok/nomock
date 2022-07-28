package com.github.potat0x.nomock.examples.repositories;

import com.github.potat0x.nomock.InMemoryCrudRepository;
import com.github.potat0x.nomock.examples.bookapp.BookEntity;

import static com.github.potat0x.nomock.IdGenerators.LONG_GENERATOR;

public class InMemoryCrudBookRepository extends InMemoryCrudRepository<BookEntity, Long> implements CrudBookRepository {
    public InMemoryCrudBookRepository() {
        super(1L, LONG_GENERATOR); //use one of predefined generators
//        super(1L, id -> id + 1); //or define your own generator
    }
}
