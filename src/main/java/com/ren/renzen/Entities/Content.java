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

@Getter@Setter
@Document(collection = "Contents")
public class Content {

    @Id
    private String id;

    //data
    private String content;
    private String header;
    private int upVotes;
    private int downVotes;

    //ids
    private String author;

    List<String> sections;

    //constructors
    public Content() {}

    public Content(String content, String author) {
        this.content = content;
        this.author= author;
    }

}
