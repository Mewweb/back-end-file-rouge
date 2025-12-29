package com.m2l.m2l.configuration;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.m2l.m2l.entities.User;
import com.m2l.m2l.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.warn("Tentative d'accès au système par {} à {}", email ,LocalDateTime.now());
        User user = userRepository.findByEmail(email);
        if (user == null) {
            log.error("Echec de connexion : accès réfusé à {}", email );
            throw new UsernameNotFoundException("Aucun utilisateur nommé " + email);
        }
        return new UserDetailsImpl(user);
    }
}