package com.system.slam.repository.impl;

import com.system.slam.entity.Status;
import com.system.slam.repository.StatusRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class StatusRepositoryImpl implements StatusRepository {

    @Override
    public Optional<Status> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Status> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Status> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Status> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Status getOne(Long aLong) {
        return null;
    }

    @Override
    public Status getById(Long aLong) {
        return null;
    }

    @Override
    public Status getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Status> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Status> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Status> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Status> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Status> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Status> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Status, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Status> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Status> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Status> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Status> findAll() {
        return null;
    }

    @Override
    public List<Status> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Status entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Status> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Status> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Status> findAll(Pageable pageable) {
        return null;
    }
}
