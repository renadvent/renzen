package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@Document(collection = "Communities")
public class Community {

    @Id
    String id;

    //data
    String name="";
    String description="";

    //ids
    String creator="";

    //arrays of ids
    List<String> topics = new ArrayList<>();
    List<String> articles = new ArrayList<>();
    List<String> questions = new ArrayList<>();
    List<String> users = new ArrayList<>();
    List<String> moderators = new ArrayList<>();

    //-------------------constructors---------------------

    public Community(){}

    public Community(String name, String description) {
        this.name=name;
        this.description=description;
    }

}
