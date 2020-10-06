package com.ren.renzen.Exceptions;

import lombok.Data;

@Data
public class UserNameAlreadyExistsResponse {
    private String username;

    UserNameAlreadyExistsResponse(String username){
        this.username=username;
    }

}
