package io.stack.pj.user.auth;

import io.stack.pj.shared.PropertyNames;
import io.stack.pj.user.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Slf4j
@Component
public class CustomAuthSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    // Session timeout, default is 30 mins
    @Value(PropertyNames.SESSION_TIMEOUT)
    private final Integer SESSION_TIMEOUT = 60 * 30;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {

        log.debug("Session timeout {} in seconds",SESSION_TIMEOUT);

        request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
        handle(request, response, authentication);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication)
            throws IOException {

        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.debug(
                    "Response has already been committed. Unable to redirect to "
                            + targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    public String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            log.debug("Authority:- {}", grantedAuthority.getAuthority().toString());
            if (grantedAuthority.getAuthority().equals(Role.ROLE_USER.toString())) {
                log.debug("User page ");
                return "/user";
            } else if (grantedAuthority.getAuthority().equals(Role.ROLE_ADMIN.toString())) {
                log.debug("Admin page");
                return "/admin";
            }
        }
        throw new IllegalStateException();
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
