package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Document(collection="Pages")
public class Page {

    @Id
    private String id;
    private List<String> section_refs;

    private Page(){}

    public Page(String pageName){
        this.id=pageName;
    }
}
