package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter@Setter
@Document(collection = "Definitions")
public class Definition {

    @Field(value = "id")
    private String id;

    @Field(value = "definition")
    private String definition;

    public Definition() {}

    public Definition(String definition){
        this.definition=definition;
    }

}
