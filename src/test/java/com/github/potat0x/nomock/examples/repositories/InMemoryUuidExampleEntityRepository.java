package com.github.potat0x.nomock.examples.repositories;

import com.github.potat0x.nomock.IdGenerators;
import com.github.potat0x.nomock.InMemoryCrudRepository;

import java.util.UUID;

public final class InMemoryUuidExampleEntityRepository extends InMemoryCrudRepository<UuidExampleEntity, UUID>
        implements UuidExampleEntityRepository {
    public InMemoryUuidExampleEntityRepository() {
        super(IdGenerators.uuidGenerator());
    }
}
