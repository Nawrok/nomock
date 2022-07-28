package com.github.potat0x.nomock.examples.repositories;

import com.github.potat0x.nomock.examples.bookapp.BookEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("production")
public interface PagingAndSortingBookRepository extends PagingAndSortingRepository<BookEntity, Long> {
}
