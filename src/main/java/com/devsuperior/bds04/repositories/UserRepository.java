package com.devsuperior.bds04.repositories;

import com.devsuperior.bds04.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
