package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Document(collection = "Communities")
public class Community {


    //when an article is saved
    //update



    class dualList {
        List<String> names = new ArrayList<>();
        List<String> links = new ArrayList<>();
    }

    class dual {
        String name = "";
        String link = "";
    }

    //direct data

    @Id
    String id;
    //String _id;

    String name="";
    String description="";
    String creator="";

    //when saving an article, it must have a community and topic

    //used for shallow loading CommunityArticleList
    List<topic> topics = new ArrayList<>();
    class topic {
        String name="";
        List<dual> articles = new ArrayList<>();
    }





    //dualList articlesX = new dualList();
    dualList usersX = new dualList();
    dualList moderatorsX = new dualList();



    class Shallow {

    }

    //link data

    private List<String> articles = new ArrayList<>(); //links to content //used to render hierarchy
    List<String> comDiscussionSections = new ArrayList<>();

    List<String> users = new ArrayList<>();
    List<String> moderators = new ArrayList<>();

    List<String> articleSections = new ArrayList<>();

    //private List<String> topics = new ArrayList<>();

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
