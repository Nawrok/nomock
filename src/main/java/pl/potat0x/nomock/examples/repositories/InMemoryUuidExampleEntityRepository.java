package pl.potat0x.nomock.examples.repositories;

import pl.potat0x.nomock.inmemoryrepository.repository.InMemoryCrudRepository;

import java.util.UUID;

import static pl.potat0x.nomock.inmemoryrepository.repository.IdGenerators.UUID_GENERATOR;

final class InMemoryUuidExampleEntityRepository extends InMemoryCrudRepository<UuidExampleEntity, UUID> implements UuidExampleEntityRepository {
    public InMemoryUuidExampleEntityRepository() {
        super(UUID_GENERATOR);
    }
}
