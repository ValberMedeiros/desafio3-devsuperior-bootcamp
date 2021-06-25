package com.devsuperior.bds04.services;

import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.dto.UserDto;
import com.devsuperior.bds04.repositories.UserRepository;
import com.devsuperior.bds04.controller.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Page<UserDto> findAll(Pageable pageable) {
        Page<User> list =  repository.findAll(pageable);
        return list
                .map(UserDto::new);
    }

    public UserDto findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.map(UserDto::new).orElseThrow(() -> {
            throw new ResourceNotFoundException("Entity not found.");
        });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByEmail(username);
        if(user == null) {
            logger.error("User not found: {}", username);
            throw new UsernameNotFoundException("EMail not found");
        }
        logger.info("User found: {}", username);
        return user;
    }
}
