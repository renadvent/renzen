package com.ren.renzen.DomainObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Data for an Article
 */
@Data
@NoArgsConstructor
@Document(collection = "Articles")
public class ArticleDO {

    @Id
    ObjectId _id;
    String topic;
    String name;
    String description;
    ObjectId userID;
    ObjectId communityID;
    ObjectId discussionID;
    List<ArticleSectionDO> articleSectionDOList;

    List<ObjectId> imageIDs = new ArrayList<ObjectId>();

    public ArticleDO(String name, String description, ObjectId userID, ObjectId communityID,
                     List<ArticleSectionDO> articleSectionDOList) {
        this.name = name;
        this.description = description;
        this.userID = userID;
        this.communityID = communityID;
        this.articleSectionDOList = articleSectionDOList;
    }
}
