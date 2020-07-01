package com.ren.renzen.Entities;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

//content

@QueryEntity
@Getter@Setter
@Document(collection = "Notes")
public class X_Note {

//    @MongoId
//    @Field(value="id")
    @Id
    private String id;
    //private ObjectId id;

    @Field(value="content")
    private  String content;

    //for retrieval
    private String pageSource;
    private String type;

    private String date;
    private String time;
    private String noteType;
    private String user;

    private int upVotes;
    private int downVotes;

    private String threadID;

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
