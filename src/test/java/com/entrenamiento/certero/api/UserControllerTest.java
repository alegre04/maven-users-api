package com.entrenamiento.certero.api;

import com.entrenamiento.certero.domain.User;
import com.entrenamiento.certero.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User("Carlos Pérez", 28);
    }

    @Test
    void testGetUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Carlos Pérez"));
    }

    @Test
    void testAddUser() throws Exception {
        doNothing().when(userService).addUser(user);

        mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content("{\"name\":\"Carlos Pérez\",\"age\":28}"))
                .andExpect(status().isOk());

        verify(userService, times(1)).addUser(any(User.class));
    }

    @Test
    void testRemoveUser() throws Exception {
        when(userService.removeUser("Carlos Pérez")).thenReturn(true);

        mockMvc.perform(delete("/api/users/Carlos Pérez"))
                .andExpect(status().isOk());

        verify(userService, times(1)).removeUser("Carlos Pérez");
    }

    @Test
    void testGetUserByName() throws Exception {
        when(userService.getUserByName("Carlos Pérez")).thenReturn(user);

        mockMvc.perform(get("/api/users/Carlos Pérez"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Carlos Pérez"));
    }
}
