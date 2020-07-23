package com.ren.renzen.Entities;

import com.ren.renzen.Repos.Article_Repository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@Document(collection="Articles")
public class Article {

    @Id
    private String id;

    //data
    private String name;
    private String description;

    //ids
    private String author;
    private String community;

    //arrays of ids
    List<String> contents = new ArrayList<>();

    //constructors
    public Article(){}

}
