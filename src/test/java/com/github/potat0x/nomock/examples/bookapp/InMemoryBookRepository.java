package com.github.potat0x.nomock.examples.bookapp;

import com.github.potat0x.nomock.InMemoryJpaRepository;

import static com.github.potat0x.nomock.IdGenerators.LONG_GENERATOR;

class InMemoryBookRepository extends InMemoryJpaRepository<BookEntity, Long> implements BookRepository {
    public InMemoryBookRepository() {
        super(1L, LONG_GENERATOR);
    }
}
