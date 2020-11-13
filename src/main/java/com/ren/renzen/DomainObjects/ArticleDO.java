package com.ren.renzen.DomainObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    String topic="none";
    String description;

    String creatorName;

    //TODO implement work
    String workName="";


    //NEW
    List<String> tagList = new ArrayList<>();
    String postText="";
    String postType="none";
    List<Comment> comments = new ArrayList<>();
    List<PollOption> pollOptions = new ArrayList<>();


    ObjectId creatorID;
//    Optional<ObjectId> creatorID;
    ObjectId communityID;
    ObjectId discussionID;

    List<ArticleSectionDO> articleSectionDOList;

    int likes=0;
    int dislikes=0;

    //public article view setting
    boolean visibleInCommunity = true;

    List<ObjectId> imageIDs = new ArrayList<ObjectId>();

    String image="";

    @Data
    @NoArgsConstructor
    public static class Comment{
        String comment;
        ObjectId author;
        String authorName="";
    }

    @Data
    @NoArgsConstructor
    public static class PollOption{
        String name;
        int votes;
    }
}
