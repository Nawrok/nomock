package com.github.potat0x.nomock.examples.repositories;

import com.github.potat0x.nomock.InMemoryJpaRepository;
import com.github.potat0x.nomock.examples.bookapp.BookEntity;

import static com.github.potat0x.nomock.IdGenerators.LONG_GENERATOR;

public class InMemoryJpaBookRepository extends InMemoryJpaRepository<BookEntity, Long> implements JpaBookRepository {
    public InMemoryJpaBookRepository() {
        super(1L, LONG_GENERATOR);
    }
}
