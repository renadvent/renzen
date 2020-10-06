package com.ren.renzen.Exceptions;

import lombok.Data;

@Data
public class InvalidLoginResponse {
    private String username;
    private String password;

    public InvalidLoginResponse(){
        username="Invalid Username";
        password="Invalid Password";
    }
}
