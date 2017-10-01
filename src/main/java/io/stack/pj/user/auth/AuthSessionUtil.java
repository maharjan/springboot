package io.stack.pj.user.auth;

import io.stack.pj.user.impl.DefaultUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Slf4j
public final class AuthSessionUtil {

    public static final DefaultUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        final Object principal = authentication.getPrincipal();
        DefaultUserDetails user = null;
        if (principal instanceof DefaultUserDetails) {
            user = ((DefaultUserDetails) principal);
        }
        return user == null ? null : user;
    }

    private AuthSessionUtil() {
    }
}