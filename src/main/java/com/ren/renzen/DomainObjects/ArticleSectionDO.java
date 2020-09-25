package com.ren.renzen.DomainObjects;

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

    ArticleSectionDO(String header, String body) {
        this.header = header;
        this.setBody(body);
    }
}
