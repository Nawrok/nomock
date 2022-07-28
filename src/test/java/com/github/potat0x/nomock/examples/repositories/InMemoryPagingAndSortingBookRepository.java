package com.github.potat0x.nomock.examples.repositories;

import com.github.potat0x.nomock.InMemoryPagingAndSortingRepository;
import com.github.potat0x.nomock.examples.bookapp.BookEntity;

import static com.github.potat0x.nomock.IdGenerators.LONG_GENERATOR;

public class InMemoryPagingAndSortingBookRepository extends InMemoryPagingAndSortingRepository<BookEntity, Long>
        implements PagingAndSortingBookRepository {
    public InMemoryPagingAndSortingBookRepository() {
        super(1L, LONG_GENERATOR);
    }
}
