package com.ren.renzen.DomainObjects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@Document(collection="Users")
public class UserDomainObject extends BaseEntity {

    String password;

    List<String> articleIDs=new ArrayList<>();
    List<String> communityIDs=new ArrayList<>();

    //for register and login
    public UserDomainObject(String name, String password){
        this.setName(name);
        this.setPassword(password);
    }

}
