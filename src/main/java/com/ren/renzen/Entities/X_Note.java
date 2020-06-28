package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Getter@Setter
@Document(collection = "Notes")
public class X_Note {

    @Field(value="id")
    private String id;

    @Field(value="content")
    private  String content;

    //for retrieval
    private String pageSource;
    private String type;

    private String date;
    private String time;
    private String noteType;
    private String user;

    @Field(value="comments")
    private List<String> comments;

    public X_Note () {};

    public X_Note(String content, String user) {
        this.content = content;
        this.user= user;
    }

    public X_Note(String content, String user, String pageSource) {
        this.content = content;
        this.user= user;
        this.pageSource=pageSource;
    }

    public X_Note(String content) {
        this.content = content;
    }

}
