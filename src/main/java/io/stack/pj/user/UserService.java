package io.stack.pj.user;

import io.stack.pj.user.resource.UserCreateRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
public interface UserService {
    long countUsers();

    void createUser(UserCreateRequest request);
}
