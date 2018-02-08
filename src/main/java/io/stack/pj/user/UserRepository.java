package io.stack.pj.user;

import io.stack.pj.shared.DefaultJpaRepository;
import io.stack.pj.user.impl.Users;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
public interface UserRepository extends DefaultJpaRepository<Users> {

    //    @Query("SELECT u FROM Users u WHERE u.username=?1")
    Optional<Users> findByUsername(String username);
}


