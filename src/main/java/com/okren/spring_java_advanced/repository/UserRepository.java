package com.okren.spring_java_advanced.repository;

import com.okren.spring_java_advanced.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);// Spring Data JPA самостійно створить відповідний метод з відповідним SQL запитом і буде call-фти entity-manager .

}
