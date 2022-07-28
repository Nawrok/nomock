package com.github.potat0x.nomock.examples.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UuidExampleEntityRepository extends CrudRepository<UuidExampleEntity, UUID> {
}
