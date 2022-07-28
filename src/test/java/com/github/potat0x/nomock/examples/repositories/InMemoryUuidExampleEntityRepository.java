package com.github.potat0x.nomock.examples.repositories;

import com.github.potat0x.nomock.InMemoryCrudRepository;

import java.util.UUID;

import static com.github.potat0x.nomock.IdGenerators.UUID_GENERATOR;

public final class InMemoryUuidExampleEntityRepository extends InMemoryCrudRepository<UuidExampleEntity, UUID>
        implements UuidExampleEntityRepository {
    public InMemoryUuidExampleEntityRepository() {
        super(UUID_GENERATOR);
    }
}
