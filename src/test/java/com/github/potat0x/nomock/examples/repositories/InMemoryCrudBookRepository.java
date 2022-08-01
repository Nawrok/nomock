package com.github.potat0x.nomock.examples.repositories;

import com.github.potat0x.nomock.IdGenerators;
import com.github.potat0x.nomock.InMemoryCrudRepository;
import com.github.potat0x.nomock.examples.bookapp.BookEntity;

public class InMemoryCrudBookRepository extends InMemoryCrudRepository<BookEntity, Long> implements CrudBookRepository {
    public InMemoryCrudBookRepository() {
        super(IdGenerators.longGenerator()); //use one of predefined generators
    }
}
