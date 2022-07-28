package pl.potat0x.nomock.examples.repositories;

import pl.potat0x.nomock.examples.bookapp.BookEntity;
import pl.potat0x.nomock.inmemoryrepository.repository.InMemoryPagingAndSortingRepository;

import static pl.potat0x.nomock.inmemoryrepository.repository.IdGenerators.LONG_GENERATOR;

public class InMemoryPagingAndSortingBookRepository extends InMemoryPagingAndSortingRepository<BookEntity, Long> implements PagingAndSortingBookRepository {
    public InMemoryPagingAndSortingBookRepository() {
        super(1L, LONG_GENERATOR);
    }
}
