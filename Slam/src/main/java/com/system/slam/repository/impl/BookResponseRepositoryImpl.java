package com.system.slam.repository.impl;

import com.system.slam.entity.BookResponse;
import com.system.slam.repository.BookResponseRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class BookResponseRepositoryImpl implements BookResponseRepository {
    @Override
    public void flush() {

    }

    @Override
    public <S extends BookResponse> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends BookResponse> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<BookResponse> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public BookResponse getOne(Long aLong) {
        return null;
    }

    @Override
    public BookResponse getById(Long aLong) {
        return null;
    }

    @Override
    public BookResponse getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends BookResponse> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends BookResponse> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends BookResponse> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends BookResponse> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BookResponse> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends BookResponse> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends BookResponse, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends BookResponse> S save(S entity) {
        return null;
    }

    @Override
    public <S extends BookResponse> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BookResponse> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<BookResponse> findAll() {
        return null;
    }

    @Override
    public List<BookResponse> findAllById(Iterable<Long> longs) {
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
    public void delete(BookResponse entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends BookResponse> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<BookResponse> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<BookResponse> findAll(Pageable pageable) {
        return null;
    }
}
