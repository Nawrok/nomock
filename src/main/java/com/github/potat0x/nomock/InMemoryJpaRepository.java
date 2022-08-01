package com.github.potat0x.nomock;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.data.util.Streamable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryJpaRepository<T, ID> extends InMemoryPagingAndSortingRepository<T, ID>
        implements JpaRepository<T, ID> {

    public InMemoryJpaRepository(IdGenerator<ID> idGenerator) {
        super(idGenerator);
    }

    @Override
    public List<T> findAll() {
        return Streamable.of(super.findAll()).toList();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return Streamable.of(super.findAll(sort)).toList();
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return Streamable.of(super.findAllById(ids)).toList();
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return Streamable.of(super.saveAll(entities)).toList();
    }

    @Override
    public void flush() {
        //nothing to flush
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return save(entity);
    }

    @Override
    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        return saveAll(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<T> entities) {
        deleteAll(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<ID> ids) {
        deleteAllById(ids);
    }

    @Override
    public void deleteAllInBatch() {
        deleteAll();
    }

    @Override
    public T getOne(ID id) {
        return getReferenceById(id);
    }

    @Override
    public T getById(ID id) {
        return getReferenceById(id);
    }

    @Override
    public T getReferenceById(ID id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id=" + id + " not found"));
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException();
    }

}
