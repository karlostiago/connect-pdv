package com.connectpdv.pdv.repository;

import com.util.commons.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserNameEquals(String userName);
    User findByPersonIdEquals(Long personId);
    User findByUserNameContaining(String userName);
}
