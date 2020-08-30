package com.spring.course.springit.repository;

import com.spring.course.springit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
