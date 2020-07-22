package com.ren.renzen.Entities;

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

    private String author;
    private String community;

    private String articleName;
    private String articleDescription;
    private String articleTags;
    private String articleAddToSection;

    private List<String> contentArray = new ArrayList<>();
    private List<String> pageArray = new ArrayList<>();//page for q&a for each section of article

    public Article(){}

    public Article(String articleName,String articleDescription,
                   String articleTags, String articleAddToSection){
        this.articleName=articleName;
        this.articleDescription=articleDescription;
        this.articleTags=articleTags;
        this.articleAddToSection=articleAddToSection;
    }


}
