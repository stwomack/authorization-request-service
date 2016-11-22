package io.pivotal;

import io.pivotal.domain.AuthorizationRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.io.Serializable;
import java.util.Collection;

@SpringBootApplication
public class AuthorizationRequestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationRequestServiceApplication.class, args);
    }
}

@RepositoryRestResource
interface AuthorizationRequestRepository extends JpaRepository<AuthorizationRequest, Long> {
}