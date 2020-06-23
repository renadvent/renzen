package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter@Setter
@Document(collection = "Definitions")
public class Definition {

    @Id
    private String id;

    @Field("definition")
    private String definition;

    public Definition(String definition){
        this.definition=definition;
    }

}
