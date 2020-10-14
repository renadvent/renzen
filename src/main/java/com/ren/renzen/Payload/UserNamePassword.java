package com.ren.renzen.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserNamePassword {

    @NotBlank(message = "username must not be blank")
    String username;

    @NotBlank(message = "password must not be blank")
    String password;

    String confirmPassword; //?

    public UserNamePassword(String username, String password) {
        this.username = username;
        this.password = password;
    }
}


