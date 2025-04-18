package com.system.nizopay.persistence.orm.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface DefaultJpaRepository<T, ID> extends Repository<T, ID>{
    Optional<T> findById(ID id);
    <S extends T> S save(S entity);
}
