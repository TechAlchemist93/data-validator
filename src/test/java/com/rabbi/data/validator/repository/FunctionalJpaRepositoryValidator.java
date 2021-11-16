package com.rabbi.data.validator.repository;

import com.rabbi.data.validator.FailCase;
import com.rabbi.data.validator.FunctionalDataValidator;
import io.vavr.control.Try;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalJpaRepositoryValidator extends JpaRepository<TestEntity, String>, FunctionalDataValidator<TestEntity> {

    TestEntity findByName(String name);

    default Try<TestEntity> findById(String id, FailCase failcase) {
        return validate(findById(id), "id", id, failcase);
    }

    default Try<TestEntity> findByName(String name, FailCase failcase) {
        return validate(findByName(name), "name", name, failcase);
    }
}
