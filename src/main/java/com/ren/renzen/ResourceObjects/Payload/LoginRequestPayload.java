package com.ren.renzen.ResourceObjects.Payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestPayload {
    @NotBlank(message = "username must not be blank")
    String username;

    @NotBlank(message = "password must not be blank")
    String password;
}
