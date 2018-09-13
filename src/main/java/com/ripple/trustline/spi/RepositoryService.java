package com.ripple.trustline.spi;

/**
 * @author vreddy
 */
public interface RepositoryService<T, R> {

    T findById(R id);

    void persist(T entity);
}
