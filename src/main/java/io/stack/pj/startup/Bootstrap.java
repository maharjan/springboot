package io.stack.pj.startup;

import io.stack.pj.user.*;
import io.stack.pj.user.resource.UserCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Slf4j
@Component
public class Bootstrap {

    private final UserService userService;

    @Autowired
    public Bootstrap(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void defaultUser() {
        if (userService.countUsers() == 0) {
            log.debug("Creating admin user: admin/sa@1234");
            List<Role> adminRole = new ArrayList<>();
            adminRole.add(Role.ROLE_ADMIN);
            userService.createUser(UserCreateRequest.builder()
                    .username("admin")
                    .password("sa@1234")
                    .roles(adminRole)
                    .build());

            log.debug("Creating user: user/pj@1234");
            List<Role> userRole = new ArrayList<>();
            userRole.add(Role.ROLE_USER);
            userService.createUser(UserCreateRequest.builder()
                    .username("user")
                    .password("pj@1234")
                    .roles(userRole)
                    .build());
        }
    }
}
