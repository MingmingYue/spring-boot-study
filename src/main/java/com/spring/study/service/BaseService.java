package com.spring.study.service;

import com.spring.study.mapper.dao.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/8.
 */
@FunctionalInterface
public interface BaseService<E, ID extends Serializable> {

    BaseDao<E, ID> getRepository();

    default E get(ID id) {
        return getRepository().getOne(id);
    }

    default List<E> getAll() {
        return getRepository().findAll();
    }

    default Long getTotalCount() {
        return getRepository().count();
    }

    default E save(E entity) {

        return getRepository().save(entity);
    }

    default E update(E entity) {
        return getRepository().saveAndFlush(entity);
    }

    default Iterable<E> saveOrUpdateAll(Iterable<E> entities) {
        return getRepository().saveAll(entities);
    }

    default void delete(E entity) {
        getRepository().delete(entity);
    }

    default void delete(ID id) {
        getRepository().deleteById(id);
    }

    default void delete(Iterable<E> entities) {
        getRepository().deleteAll(entities);
    }

    default void flush() {
        getRepository().flush();
    }

    default List<E> findAll(Specification<E> spec) {
        return getRepository().findAll(spec);
    }

    default Page<E> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    default Page<E> findAll(Specification<E> spec, Pageable pageable) {
        return getRepository().findAll(spec, pageable);
    }

    default long count(Specification<E> spec) {
        return getRepository().count(spec);
    }
}
