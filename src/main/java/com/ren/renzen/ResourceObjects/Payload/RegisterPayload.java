package com.ren.renzen.ResourceObjects.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class RegisterPayload {

    @NotBlank(message = "username must not be blank")
    String username;

    @NotBlank(message = "password must not be blank")
    String password;

    @NotBlank(message = "confirm password must not be blank")
    String confirmPassword; //?

    @NotBlank(message = "email must not be blank")
    String email;

//    public UserNamePassword(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
}


