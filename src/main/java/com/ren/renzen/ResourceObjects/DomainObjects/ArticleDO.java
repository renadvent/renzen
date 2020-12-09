package com.ren.renzen.ResourceObjects.DomainObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.Date;
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
    ObjectId creatorID;
    ObjectId communityID;

    //maybe?
    String creatorName;

    int likes = 0;
    int dislikes = 0;
    List<ObjectId> userLikeIDs = new ArrayList<>();
    List<ObjectId> userDislikeIDs = new ArrayList<>();

    Boolean isDraft = true;
    String articleName = "";
    String workName = "";

    List<String> tagList = new ArrayList<>();
    List<Comment> comments = new ArrayList<>();
    List<PollOption> pollOptions = new ArrayList<>();

    List<ArticleSectionDO> articleSectionDOList = new ArrayList<>();

    String postImageURL = "";

    Date created_at = new Date();
    List<Date> updated_at = new ArrayList<>();

    @Data
    @NoArgsConstructor
    public static class Comment {
        int likes;
        int dislikes;

        List<ObjectId> userLikeIDs = new ArrayList<>();
        List<ObjectId> userDislikeIDs = new ArrayList<>();

        String comment;
        ObjectId author;
        String authorName = "";
    }

    @Data
    @NoArgsConstructor
    public static class PollOption {
        String name;
        int votes;
    }

    @Data
    @NoArgsConstructor
    public static class ArticleSectionDO {
        String header;
        String body;
    }
}
