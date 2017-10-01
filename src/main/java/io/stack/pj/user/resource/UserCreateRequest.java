package io.stack.pj.user.resource;

import io.stack.pj.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Getter
@Setter
@Builder
@ToString
public class UserCreateRequest implements Serializable {

    private String username;
    private String password;
    private List<Role> roles;
}
