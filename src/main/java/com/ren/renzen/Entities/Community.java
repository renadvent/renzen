package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "Communities")
public class Community {


    @Id
    String id;
    //String _id;

    String name="";
    String description="";

    String creator="";

    private List<String> articles = new ArrayList<>(); //links to content //used to render hierarchy
    List<String> comDiscussionSections = new ArrayList<>();

    List<String> users = new ArrayList<>();
    List<String> moderators = new ArrayList<>();

    List<String> articleSections = new ArrayList<>();

    private List<String> topics = new ArrayList<>();

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
