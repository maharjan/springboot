package io.stack.pj.usertest;

import io.stack.pj.user.LoginController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Prajin Maharjan
 * @since Feb 08, 2018
 */
//@RunWith(SpringRunner.class)
//@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
    public void getLogin() throws Exception {
        mockMvc.perform(get("/")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andDo(print());
    }

//    @Test(expected = NullPointerException.class)
    public void getAdmin() throws Exception {
        mockMvc.perform(get("/admin")
                .accept(MediaType.TEXT_HTML))
                .andDo(print());
    }
}