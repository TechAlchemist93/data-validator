package com.rabbi.data.validator.repository;

import com.rabbi.data.validator.FailCase;
import com.rabbi.data.validator.ReactiveDataValidator;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveJpaRepositoryValidator extends ReactiveCrudRepository<TestEntity, String>, ReactiveDataValidator<TestEntity> {

    Mono<TestEntity> findByName(String name);

    default Mono<TestEntity> findById(String id, FailCase failcase) {
        return validate(findById(id), "id", id, failcase);
    }

    default Mono<TestEntity> findByName(String name, FailCase failcase) {
        return validate(findByName(name), "name", name, failcase);
    }
}
