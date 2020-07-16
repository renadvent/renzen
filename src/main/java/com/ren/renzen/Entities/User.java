package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter@Setter
@Document(collection="Users")
public class User {

    @Id
    private String id;

    private String userName;
    private String password;

    private List<String> contentList;
    private List<String> communities;

    private int reputation;

    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

    private List<String> friends;
    private List<String> conversations;



}
