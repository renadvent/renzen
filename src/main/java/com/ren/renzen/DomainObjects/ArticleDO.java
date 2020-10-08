package com.ren.renzen.DomainObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

/**
 * Data for an Article
 */
@Data
@NoArgsConstructor
@Document(collection = "Articles")
public class ArticleDO {

    @MongoId
    ObjectId _id;

    String articleName;
    String topic;
    String description;

    String creatorName;

    ObjectId creatorID;
    ObjectId communityID;
    ObjectId discussionID;

    List<ArticleSectionDO> articleSectionDOList;

    //public article view setting
    boolean visibleInCommunity = true;

    List<ObjectId> imageIDs = new ArrayList<ObjectId>();
}
