package com.system.slam.repository.impl;

import com.system.slam.entity.BookLiterary;
import com.system.slam.repository.BookLiteraryRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class BookLiteraryRepositoryImpl implements BookLiteraryRepository {
    @Override
    public void flush() {

    }

    @Override
    public <S extends BookLiterary> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends BookLiterary> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<BookLiterary> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public BookLiterary getOne(Long aLong) {
        return null;
    }

    @Override
    public BookLiterary getById(Long aLong) {
        return null;
    }

    @Override
    public BookLiterary getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends BookLiterary> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends BookLiterary> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends BookLiterary> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends BookLiterary> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BookLiterary> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends BookLiterary> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends BookLiterary, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends BookLiterary> S save(S entity) {
        return null;
    }

    @Override
    public <S extends BookLiterary> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BookLiterary> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<BookLiterary> findAll() {
        return null;
    }

    @Override
    public List<BookLiterary> findAllById(Iterable<Long> longs) {
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
    public void delete(BookLiterary entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends BookLiterary> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<BookLiterary> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<BookLiterary> findAll(Pageable pageable) {
        return null;
    }
}
