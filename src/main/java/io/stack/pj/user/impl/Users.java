package io.stack.pj.user.impl;

import io.stack.pj.shared.AbstractEntity;
import io.stack.pj.user.UserStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Getter
@Setter
@ToString
@Entity
public class Users extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private UserStatus status= UserStatus.INACTIVE;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<UserRoles> roles;

}
