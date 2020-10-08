package com.ren.renzen.DomainObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * contains the content for a section of an article
 */
@Data
@NoArgsConstructor
public class ArticleSectionDO {
    String header;
    String body;
    String imageID;

    ArticleSectionDO(String header, String body) {
        this.header = header;
        this.setBody(body);
    }

    public ArticleSectionDO(String imageID){
        this.imageID=imageID;
    }
}
