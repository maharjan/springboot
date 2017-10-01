package io.stack.pj.user.impl;

import io.stack.pj.user.UserStatus;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Getter
public class DefaultUserDetails implements UserDetails {

    private Users user;
    private final Collection<GrantedAuthority> roles;

    public DefaultUserDetails(Users user, Collection<GrantedAuthority> roles) {
        this.user = user;
        this.roles=roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(UserStatus.ACTIVE);
    }
}
