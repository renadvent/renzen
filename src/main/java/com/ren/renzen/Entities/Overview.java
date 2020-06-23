package com.ren.renzen.Entities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter@Setter
@Document(collection="Overviews")
public class Overview {

    @Field(value="author")
    private String author;

    @Field(value="content")
    private String content;

    public Overview() {}

    public Overview(String author, String content){
        this.author=author;
        this.content=content;
    }
}
