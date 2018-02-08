package io.stack.pj.user;

import io.stack.pj.user.resource.UserCreateRequest;
import io.stack.pj.user.resource.UserInfoResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
public interface UserService {
    long countUsers();

    void createUser(UserCreateRequest request);

    @Secured(value = "ROLE_ADMIN")
    List<UserInfoResponse> getUsers();
}
