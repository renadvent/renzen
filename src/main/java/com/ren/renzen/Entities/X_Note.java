package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter@Setter
@Document(collection = "Notes")
public class X_Note {

    @Field(value="id")
    private String id;

    @Field(value="definition")
    //private  String content;
    private  String definition;

    private String date;
    private String time;
    private String noteType;
    private String user;

    private X_Note () {};

    public X_Note(String definition, String user) {
        this.definition = definition;
        this.user= user;
    }

    public X_Note(String definition) {
        this.definition = definition;
    }

}
