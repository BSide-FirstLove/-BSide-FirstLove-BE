package com.bside.love.user.repository;

import com.bside.love.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findMemberById(Long memberId);

    @Query(value="SELECT * FROM user WHERE socialId = :socialId", nativeQuery = true)
    User findBySocialId(String socialId);
}