package com.rabbi.data.validator.repository;

import com.rabbi.data.validator.FailCase;
import com.rabbi.data.validator.SimpleDataValidator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRepositoryValidator extends JpaRepository<TestEntity, String>, SimpleDataValidator<TestEntity> {

    TestEntity findByName(String name);

    default TestEntity findById(String id, FailCase failcase) {
        return validate(findById(id), "id", id, failcase);
    }

    default TestEntity findByName(String name, FailCase failcase) {
        return validate(findByName(name), "name", name, failcase);
    }
}
