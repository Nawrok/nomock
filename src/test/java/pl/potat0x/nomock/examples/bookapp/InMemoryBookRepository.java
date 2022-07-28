package pl.potat0x.nomock.examples.bookapp;

import pl.potat0x.nomock.inmemoryrepository.repository.InMemoryJpaRepository;

import static pl.potat0x.nomock.inmemoryrepository.repository.IdGenerators.LONG_GENERATOR;

class InMemoryBookRepository extends InMemoryJpaRepository<BookEntity, Long> implements BookRepository {
    public InMemoryBookRepository() {
        super(1L, LONG_GENERATOR);
    }
}
