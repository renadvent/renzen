package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Setter@Getter
public class X_User {

    private @Id
    Long id;
    private String userName;

    private X_User(){}

    public X_User(String userName){
        this.userName=userName;
    }

}
