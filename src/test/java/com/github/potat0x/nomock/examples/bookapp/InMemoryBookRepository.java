package com.github.potat0x.nomock.examples.bookapp;

import com.github.potat0x.nomock.IdGenerators;
import com.github.potat0x.nomock.InMemoryJpaRepository;

class InMemoryBookRepository extends InMemoryJpaRepository<BookEntity, Long> implements BookRepository {
    public InMemoryBookRepository() {
        super(IdGenerators.longGenerator());
    }
}
