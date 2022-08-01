package com.github.potat0x.nomock.examples.repositories;

import com.github.potat0x.nomock.IdGenerators;
import com.github.potat0x.nomock.InMemoryJpaRepository;
import com.github.potat0x.nomock.examples.bookapp.BookEntity;

public class InMemoryJpaBookRepository extends InMemoryJpaRepository<BookEntity, Long> implements JpaBookRepository {
    public InMemoryJpaBookRepository() {
        super(IdGenerators.longGenerator());
    }
}
