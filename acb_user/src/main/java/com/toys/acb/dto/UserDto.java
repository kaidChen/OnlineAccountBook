package com.toys.acb.dto;

import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


@Data
public class UserDto {
    private UserDetails userDetails;
    private Long id;

    public UserDto(UserDetails userDetails, Long id) {
        setUserDetails(userDetails);
        setId(id);
    }
}
