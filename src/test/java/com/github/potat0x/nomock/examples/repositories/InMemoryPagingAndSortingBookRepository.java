package com.github.potat0x.nomock.examples.repositories;

import com.github.potat0x.nomock.IdGenerators;
import com.github.potat0x.nomock.InMemoryPagingAndSortingRepository;
import com.github.potat0x.nomock.examples.bookapp.BookEntity;

public class InMemoryPagingAndSortingBookRepository extends InMemoryPagingAndSortingRepository<BookEntity, Long>
        implements PagingAndSortingBookRepository {
    public InMemoryPagingAndSortingBookRepository() {
        super(IdGenerators.longGenerator());
    }
}
