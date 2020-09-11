package com.ren.renzen.BackEndRewrite.DomainObjects;

import lombok.Getter;
import lombok.Setter;

/**
 * contains the content for a section of an article
 */
@Getter@Setter
public class ArticleSectionDO {
    String header;
    String body;

    //upvotes,downvotes

    ArticleSectionDO(String header, String body){
        this.header=header;
        this.setBody(body);
    }
}
