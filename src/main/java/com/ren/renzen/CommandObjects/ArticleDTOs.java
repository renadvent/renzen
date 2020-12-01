package com.ren.renzen.CommandObjects;

import com.ren.renzen.DomainObjects.ArticleDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class ArticleDTOs {
    /**
     * This CO is used to return data needed to LIST an article (metadata)
     * Used where knowledge of the article is necessary, but not the contents
     * <p>
     * It is used on the Index Page, the Profile Page, and Community Page
     */
    @Data
    @NoArgsConstructor
    public static class ArticleInfoComponentCO extends RepresentationModel<ArticleInfoComponentCO> {

        //
        //    void ArticleInfoComponentCO(){
        //        this.add
        //    }

        String ACCESS_TYPE;

        //---------------

        String _id;
        ObjectId objectId;
        String name;
        String description;
        String authorID;
        String authorName;
        ProfileDTOs.ProfileInfoComponentCO profileInfoComponentCO;

        //NEW
        List<String> tagList = new ArrayList<>();
        String postText = "";
        String postType = "none";
        List<ArticleDO.Comment> comments = new ArrayList<>();
        List<ArticleDO.PollOption> pollOptions = new ArrayList<>();
        List<ObjectId> imageIDs = new ArrayList<>();

        List<ObjectId> otherPostsInWork = new ArrayList<>();
        List<String> otherPostsInWorkHex = new ArrayList<>();


        String workName = "";


        String image = "";

        //NEW
        String originalImageLink = "";


        int likes;
        int dislikes;
    }

    /**
     * This CO is used to return data needed to render an article to an article component
     */
    @Data
    @NoArgsConstructor
    public static class ArticleTabComponentCO extends RepresentationModel<ArticleTabComponentCO> {

        String ACCESS_TYPE;

        //---------------

        String _id;
        ObjectId objectId;
        String userName;
        String name;
        String description;
        ObjectId userID;
        ProfileDTOs.ProfileInfoComponentCO profileInfoComponentCO;
        ObjectId discussionID;
        List<ArticleSectionCO> articleSectionCOList = new ArrayList<>();

        int likes;
        int dislikes;

        //new

        String image = "";
        List<ArticleDO.Comment> comments = new ArrayList<>();


        //NEW
        String originalImageLink = "";


    }

    /**
     * This CO is used to return a section of an article for rendering
     */
    @Data
    @NoArgsConstructor
    public static class ArticleSectionCO {

        String ACCESS_TYPE;

        //---------------

        String _id;
        ObjectId objectId;
        String header;
        String body;
        ObjectId authorID;
        Integer upvotes;
        Integer downvotes;
        String imageID;
    }
}
