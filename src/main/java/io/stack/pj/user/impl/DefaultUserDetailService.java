package io.stack.pj.user.impl;

import io.stack.pj.user.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Slf4j
@Service
public class DefaultUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = userRepository.findByUsername(username);
        Users user = users.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        List<Role> roles = userRoleRepository.findAllByUser(user.getId());
        String userRole = null;
        for (Role role : roles) {
            userRole = (userRole == null ? role.toString() : userRole + "," + role.toString());
            log.debug(userRole);
        }
        return new DefaultUserDetails(user, AuthorityUtils.createAuthorityList(userRole));
    }
}
