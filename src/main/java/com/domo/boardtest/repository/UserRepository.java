package com.domo.boardtest.repository;

import com.domo.boardtest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(String userId);
    Optional<User> findByNickname(String nickname);

    Optional<User> findByUserIdAndUserPassword(String userId, String userPassword);
}
