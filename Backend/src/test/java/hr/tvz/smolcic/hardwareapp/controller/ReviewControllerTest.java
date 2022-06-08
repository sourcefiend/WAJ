package hr.tvz.smolcic.hardwareapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class ReviewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private final String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NTMwNDk3MSwiaWF0IjoxNjU0NzAwMTcxLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.24Q36Fm83lfwS5Cqx9t3Da-dQZLpS9xOM1whosfQKmvsA4iKGLqV3Q-usIMwAZB5iefRqNGirIolw77MOBPCiA";
    private final String userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjU1MzA1MDMwLCJpYXQiOjE2NTQ3MDAyMzAsImF1dGhvcml0aWVzIjoiUk9MRV9VU0VSIn0.wu6UKrSqrf68O1_IIHq5SAS_bsTh_Uya5TuTPz6RsNbJQgc9bMeLgaQElatS0BQt0YwDa-QICOH4uS7-jf4cXg";


    @Test
    void getAllReviews() throws Exception {
        this.mockMvc.perform
                        (get("/review")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    void getReviewsForHardware() throws Exception {
        this.mockMvc.perform
                        (get("/review")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .param("code", "001")
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}