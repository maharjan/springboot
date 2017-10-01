package io.stack.pj.user.impl;

import io.stack.pj.user.*;
import io.stack.pj.user.resource.UserCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Slf4j
@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public long countUsers() {
        return userRepository.count();
    }

    @Override
    @Transactional
    public void createUser(UserCreateRequest request) {
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        log.info("User: {}, Raw Pass: {}, Encoded Pass{}", user.getUsername(), request.getPassword(), user.getPassword());
        user.setStatus(UserStatus.ACTIVE);
        user = userRepository.save(user);

        for (Role role : request.getRoles()) {
            UserRoles adminRole = new UserRoles();
            adminRole.setRole(role);
            adminRole.setUser(user);
            userRoleRepository.save(adminRole);
        }
    }
}
