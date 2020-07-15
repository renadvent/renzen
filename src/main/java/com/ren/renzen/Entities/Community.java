package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "Communities")
public class Community {


    @Id
    String id;

    String name;
    String description;

    private List<String> articles; //links to content //used to render hierarchy
    List<String> comDiscussionSections;
    List<String> users;

    List<String> articleSections;

    public Community(){}

    public Community(String name, String description) {

        this.name=name;
        this.description=description;

        //new com section
        Page page = new Page();
        this.comDiscussionSections.add(page.getId());

        //



    }

}
