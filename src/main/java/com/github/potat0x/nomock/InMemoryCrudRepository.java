package com.github.potat0x.nomock;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class InMemoryCrudRepository<T, ID> implements CrudRepository<T, ID> {

    private final Map<ID, T> repository = new LinkedHashMap<>();
    private final EntityRipper<T, ID> entityRipper = new EntityRipper<>();
    private final IdGenerator<ID> idGenerator;

    public InMemoryCrudRepository(Supplier<ID> idSupplier) {
        this.idGenerator = new IdGenerator<>(idSupplier);
    }

    public InMemoryCrudRepository(ID initialId, UnaryOperator<ID> idSuccessorFunction) {
        this.idGenerator = new IdGenerator<>(initialId, idSuccessorFunction);
    }

    @Override
    public <S extends T> S save(S entity) {
        assertEntityNotNull(entity);
        ID id = entityRipper.getEntityId(entity).orElseGet(() -> assignIdToEntity(entity));
        repository.put(id, entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        assertEntitiesNotNull(entities);
        return Streamable.of(entities)
                .map(this::save)
                .toList();
    }

    @Override
    public Optional<T> findById(ID id) {
        assertIdNotNull(id);
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public boolean existsById(ID id) {
        assertIdNotNull(id);
        return repository.containsKey(id);
    }

    @Override
    public Iterable<T> findAll() {
        return repository.values();
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
        assertIdsNotNull(ids);
        return Streamable.of(ids)
                .map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Override
    public long count() {
        return repository.size();
    }

    @Override
    public void deleteById(ID id) {
        assertIdNotNull(id);
        if (repository.remove(id) == null) {
            throw new EmptyResultDataAccessException(String.format("No entity with id %s exists!", id), 1);
        }
    }

    @Override
    public void delete(T entity) {
        assertEntityNotNull(entity);
        entityRipper.getEntityId(entity).ifPresent(repository::remove);
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        assertIdsNotNull(ids);
        ids.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        assertEntitiesNotNull(entities);
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        repository.clear();
    }

    private ID assignIdToEntity(T entity) {
        ID id = idGenerator.nextId();
        entityRipper.setEntityId(entity, id);
        return id;
    }

    private <S extends ID> void assertIdNotNull(S id) {
        assertNotNull(id, "id must not be null");
    }

    private <S extends ID> void assertIdsNotNull(Iterable<S> ids) {
        if (ids == null) {
            throw new IllegalArgumentException("ids must not be null");
        }
        ids.forEach(this::assertIdNotNull);
    }

    private <S extends T> void assertEntityNotNull(S entity) {
        assertNotNull(entity, "entity must not be null");
    }

    private <S extends T> void assertEntitiesNotNull(Iterable<S> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("entities must not be null");
        }
        entities.forEach(this::assertEntityNotNull);
    }

    private void assertNotNull(Object object, String exceptionMessage) {
        if (object == null) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

}
