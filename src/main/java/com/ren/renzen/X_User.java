package com.ren.renzen;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter@Getter
public class X_User {

    private @Id @GeneratedValue Long id;
    private String userName;

    private X_User(){}

    public X_User(String userName){
        this.userName=userName;
    }

}
