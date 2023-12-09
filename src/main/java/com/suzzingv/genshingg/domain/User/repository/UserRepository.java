package com.suzzingv.genshingg.domain.User.repository;

import com.suzzingv.genshingg.domain.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
