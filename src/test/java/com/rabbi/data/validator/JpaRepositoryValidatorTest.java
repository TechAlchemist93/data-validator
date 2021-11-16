package com.rabbi.data.validator;

import com.rabbi.data.validator.repository.JpaRepositoryValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestApplication.class, JpaRepositoryValidator.class})
public class JpaRepositoryValidatorTest {
    @Autowired
    private JpaRepositoryValidator repo;

    @Test
    void doStuff() {
        System.out.println(repo);
    }
}
