package io.stack.pj.user;

import io.stack.pj.shared.DefaultJpaRepository;
import io.stack.pj.user.impl.UserRoles;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
public interface UserRoleRepository extends DefaultJpaRepository<UserRoles> {

    @Query("SELECT u.role FROM UserRoles u WHERE u.user.id=?1")
    List<Role> findAllByUser(Long userId);
}
