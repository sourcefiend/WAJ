package hr.tvz.smolcic.hardwareapp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.smolcic.hardwareapp.command.HardwareCommand;
import hr.tvz.smolcic.hardwareapp.enums.HardwareType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class HardwareControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private final String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NTMwNDk3MSwiaWF0IjoxNjU0NzAwMTcxLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.24Q36Fm83lfwS5Cqx9t3Da-dQZLpS9xOM1whosfQKmvsA4iKGLqV3Q-usIMwAZB5iefRqNGirIolw77MOBPCiA";
    private final String userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjU1MzA1MDMwLCJpYXQiOjE2NTQ3MDAyMzAsImF1dGhvcml0aWVzIjoiUk9MRV9VU0VSIn0.wu6UKrSqrf68O1_IIHq5SAS_bsTh_Uya5TuTPz6RsNbJQgc9bMeLgaQElatS0BQt0YwDa-QICOH4uS7-jf4cXg";

    private final Integer ID = 1;
    private final String CODE = "001";
    private final String NAME = "Testni hardver 1";
    private final Double PRICE = 1500.00;
    private final HardwareType TYPE = HardwareType.CPU;
    private final Integer STOCK = 20;

    HardwareCommand validHardwareCommand = new HardwareCommand(ID, NAME, CODE, PRICE, TYPE.toString(), STOCK);
    HardwareCommand invalidHardwareCommand = new HardwareCommand(ID, null, NAME, PRICE, null, STOCK);

    @Test
    void findAllHardware() throws Exception {
        this.mockMvc.perform
                        (get("/hardware")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    @Transactional
    void saveSuccessful() throws Exception {
        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(validHardwareCommand))
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(CODE))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.stock").value(STOCK));
    }

    @Test
    @Transactional
    void saveUnsuccessful() throws Exception {
        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(invalidHardwareCommand))
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void deleteAsAdmin() throws Exception {
        this.mockMvc.perform(
                        delete("/hardware/001")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void deleteAsUser() throws Exception {
        this.mockMvc.perform(
                        delete("/hardware/001")
                                .with(user("user").password("user").roles("USER"))
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}