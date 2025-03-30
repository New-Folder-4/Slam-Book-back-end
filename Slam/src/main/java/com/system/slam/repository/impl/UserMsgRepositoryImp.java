package com.system.slam.repository.impl;

import com.system.slam.entity.UserMsg;
import com.system.slam.repository.UserMsgRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UserMsgRepositoryImp implements UserMsgRepository {
    @Override
    public void flush() {

    }

    @Override
    public <S extends UserMsg> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends UserMsg> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserMsg> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserMsg getOne(Long aLong) {
        return null;
    }

    @Override
    public UserMsg getById(Long aLong) {
        return null;
    }

    @Override
    public UserMsg getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends UserMsg> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserMsg> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserMsg> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserMsg> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserMsg> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserMsg> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserMsg, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends UserMsg> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UserMsg> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserMsg> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<UserMsg> findAll() {
        return null;
    }

    @Override
    public List<UserMsg> findAllById(Iterable<Long> longs) {
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
    public void delete(UserMsg entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserMsg> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserMsg> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserMsg> findAll(Pageable pageable) {
        return null;
    }
}
