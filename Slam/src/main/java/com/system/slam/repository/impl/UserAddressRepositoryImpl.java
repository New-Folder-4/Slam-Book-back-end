package com.system.slam.repository.impl;

import com.system.slam.entity.UserAddress;
import com.system.slam.repository.UserAddressRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UserAddressRepositoryImpl implements UserAddressRepository {
    @Override
    public void flush() {

    }

    @Override
    public <S extends UserAddress> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends UserAddress> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserAddress> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserAddress getOne(Long aLong) {
        return null;
    }

    @Override
    public UserAddress getById(Long aLong) {
        return null;
    }

    @Override
    public UserAddress getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends UserAddress> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserAddress> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserAddress> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserAddress> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserAddress> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserAddress> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserAddress, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends UserAddress> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UserAddress> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserAddress> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<UserAddress> findAll() {
        return null;
    }

    @Override
    public List<UserAddress> findAllById(Iterable<Long> longs) {
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
    public void delete(UserAddress entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserAddress> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserAddress> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserAddress> findAll(Pageable pageable) {
        return null;
    }
}
