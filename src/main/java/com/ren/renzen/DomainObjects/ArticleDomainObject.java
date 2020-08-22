package com.ren.renzen.DomainObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter@Setter
@NoArgsConstructor
@Document(collection="Articles")
public class ArticleDomainObject extends BaseEntity {

    //ids
    private String authorID;
    private String communityID;

    class ArticleContent extends BaseContent {
        String header;
    }
}
