package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@Document(collection="Users")
public class User {

    @Id
    private String id;

    private String name;
    private String password;

    private List<String> articles=new ArrayList<>();
    private List<String> questions=new ArrayList<>();
    private List<String> communities=new ArrayList<>();
    private List<String> studyGuides=new ArrayList<>();

    //for register and login
    public User(String name,String password){
        this.name=name;
        this.password=password;
    }

}
