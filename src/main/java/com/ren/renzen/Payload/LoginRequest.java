package com.ren.renzen.Payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "username must not be blank")
    String username;

    @NotBlank(message = "password must not be blank")
    String password;




}
