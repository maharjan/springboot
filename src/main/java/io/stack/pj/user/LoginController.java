package io.stack.pj.user;

import io.stack.pj.user.auth.AuthSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Slf4j
@Controller
public class LoginController {

    @GetMapping(value = {"/", "/login"})
    public String login() {
        if (AuthSessionUtil.getCurrentUser() != null) {
            for (GrantedAuthority grantedAuthority : AuthSessionUtil.getCurrentUser().getAuthorities()) {
                if (grantedAuthority.getAuthority().equals(Role.ROLE_USER.toString())) {
                    return "redirect:/user";
                } else if (grantedAuthority.getAuthority().equals(Role.ROLE_ADMIN.toString())) {
                    return "redirect:/admin";
                }
            }
        }
        return "login";
    }

    @GetMapping(value = "/admin")
    public String getAdmin(Model model) {
        model.addAttribute("username", AuthSessionUtil.getCurrentUser().getUsername());
        return "admin";
    }

    @GetMapping(value = "/user")
    public String getUser(Model model) {
        model.addAttribute("username", AuthSessionUtil.getCurrentUser().getUsername());
        return "user";
    }

    @GetMapping(value = "/access-denied")
    public String accessDenied(Model model) {
        return "error/403";
    }
}
