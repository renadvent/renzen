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

    private String userName;
    private String password;

    private List<String> contentList=new ArrayList<>();
    public List<String> communities=new ArrayList<>();

    private int reputation;

    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

    private List<String> friends;
    private List<String> conversations;

    private List<String> articles=new ArrayList<>();
    private List<String> questions=new ArrayList<>();
    private List<String> studyGuides=new ArrayList<>();



}
