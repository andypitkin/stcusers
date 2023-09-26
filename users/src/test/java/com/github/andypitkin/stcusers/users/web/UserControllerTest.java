package com.github.andypitkin.stcusers.users.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.andypitkin.stcusers.users.UserService;
import com.github.andypitkin.stcusers.users.dao.User;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setRequestURI("/user");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockRequest));
    }

    @Test
    void createUserShouldCreateTheUserAndReturnTheLocationAsAHeader() {
        when(userService.createUser("username", "firstname", "lastname")).thenReturn(new User("username", 1, "firstname", "lastname"));

        ResponseEntity<Void> createUserResponse = userController.createUser(new UserCreationDTO("username", "firstname", "lastname"));

        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createUserResponse.getHeaders().getLocation().toString()).isEqualTo("http://localhost/user/1");
    }

    @Test
    void getUserShouldGetUserIfExists() {
        when(userService.getUserById(1)).thenReturn(Optional.of(new User("testname", 1, "firstname", "lastname")));

        ResponseEntity<UserDTO> userResponse = userController.getUser(1);

        assertThat(userResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userResponse.getBody()).isEqualTo(new UserDTO("testname", 1, "firstname", "lastname"));
    }

    @Test
    void getUserShouldReturnNotFoundIfUserDoesNotExists() {
        when(userService.getUserById(1)).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> userResponse = userController.getUser(1);

        assertThat(userResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(userResponse.getBody()).isNull();
    }
}
