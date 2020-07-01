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
@Document(collection = "Notes")
public class Content {

    @Id
    private String id;

    private String content;
    private String user;

    private int upVotes;
    private int downVotes;

    List<String> reply_refs;

    public Content() {}

    public Content(String content, String user) {
        this.content = content;
        this.user= user;
    }

    public Content(String content) {
        this.content = content;
    }

}
