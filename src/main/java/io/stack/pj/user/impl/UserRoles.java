package io.stack.pj.user.impl;

import io.stack.pj.user.Role;
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
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;

    @Column(nullable = false)
    private Role role;
}
