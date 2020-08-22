package com.ren.renzen.DomainObjects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter@Setter
public class BaseContent {

    @Id
    private String id;

    //data
    private String content;
    private int upVotes;
    private int downVotes;

    //ids
    private String authorID;

    //constructors
    public BaseContent() {}

    public BaseContent(String content, String authorID) {
        this.content = content;
        this.authorID= authorID;
    }

}
