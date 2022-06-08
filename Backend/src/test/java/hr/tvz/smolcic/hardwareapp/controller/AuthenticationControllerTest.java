package hr.tvz.smolcic.hardwareapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    Map<String, Object> userBody = new HashMap<>();
    Map<String, Object> adminBody = new HashMap<>();

    private final String AUTHENTICATION_LOGIN_URL = "/authentication/login";

    public void setUserBodyActual(Map<String, Object> userBody) {
        userBody.put("username", "user");
        userBody.put("password", "user");
        this.userBody = userBody;
    }

    public void setUserBodyFalse(Map<String, Object> userBody) {
        userBody.put("username", "user");
        userBody.put("password", "kriva_lozinka");
        this.userBody = userBody;
    }

    public void setAdminBodyActual(Map<String, Object> adminBody) {
        adminBody.put("username", "admin");
        adminBody.put("password", "admin");
        this.adminBody = adminBody;
    }

    public void setAdminBodyFalse(Map<String, Object> adminBody) {
        adminBody.put("username", "admin");
        adminBody.put("password", "kriva_lozinka");
        this.adminBody = adminBody;
    }

    @Test
    void loginUserSuccessful() throws Exception {
        setUserBodyActual(userBody);
        this.mockMvc.perform(
                post(AUTHENTICATION_LOGIN_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userBody))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.jwt").isNotEmpty()
        );
    }

    @Test
    void loginUserUnsuccessful() throws Exception {
        setUserBodyFalse(userBody);
        this.mockMvc.perform(
                        post(AUTHENTICATION_LOGIN_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userBody)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void loginAdminSuccessful() throws Exception {
        setAdminBodyActual(adminBody);
        this.mockMvc.perform(
                        post(AUTHENTICATION_LOGIN_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(adminBody))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.jwt").isNotEmpty()
                );
    }

    @Test
    void loginAdminUnsucessful() throws Exception {
        setAdminBodyFalse(adminBody);
        this.mockMvc.perform(
                        post(AUTHENTICATION_LOGIN_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(adminBody)))
                .andExpect(status().isBadRequest());
    }
}