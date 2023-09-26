package com.github.andypitkin.stcusers.users.web;

import org.junit.jupiter.api.Test;

import com.github.andypitkin.stcusers.users.dao.User;

import static org.assertj.core.api.Assertions.*;

public class UserDTOTest {

    @Test
    void fromUserShouldConvertUserEntityToUserDTO() {
        User user = new User("username", 1, "firstname", "lastname");
        UserDTO userDTO = UserDTO.fromUser(user);

        assertThat(userDTO).isEqualTo(new UserDTO("username", 1, "firstname", "lastname"));
    }
}
