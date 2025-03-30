package com.system.slam.repository.impl;

import com.system.slam.entity.UserValueCategory;
import com.system.slam.repository.UserValueCategoryRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UserValueCategoryRepositoryImpl implements UserValueCategoryRepository {
    @Override
    public void deleteAllByUserListIdUserList(Long id) {

    }

    @Override
    public List<UserValueCategory> findAllByUserList_IdUserList(Long idUserList) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends UserValueCategory> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends UserValueCategory> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserValueCategory> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserValueCategory getOne(Long aLong) {
        return null;
    }

    @Override
    public UserValueCategory getById(Long aLong) {
        return null;
    }

    @Override
    public UserValueCategory getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends UserValueCategory> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserValueCategory> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserValueCategory> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserValueCategory> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserValueCategory> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserValueCategory> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserValueCategory, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends UserValueCategory> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UserValueCategory> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserValueCategory> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<UserValueCategory> findAll() {
        return null;
    }

    @Override
    public List<UserValueCategory> findAllById(Iterable<Long> longs) {
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
    public void delete(UserValueCategory entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserValueCategory> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserValueCategory> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserValueCategory> findAll(Pageable pageable) {
        return null;
    }
}
