package com.ren.renzen.ResourceObjects.CommandObjects;

import com.ren.renzen.ResourceObjects.DomainObjects.ArticleDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleDTOs {

    @Data
    @NoArgsConstructor
    public static class ArticleInfoComponentCO extends RepresentationModel<ArticleInfoComponentCO> {

        String ACCESS_TYPE;

        //Direct Values

        String _id;
        ObjectId objectId;

        ObjectId creatorID;
        ObjectId communityID;

        int likes = 0;
        int dislikes = 0;
        List<ObjectId> userLikeIDs = new ArrayList<>();
        List<ObjectId> userDislikeIDs = new ArrayList<>();

        Boolean isDraft=true;
        String articleName="";
        String workName = "";

        List<String> tagList = new ArrayList<>();
        List<ArticleDO.Comment> comments = new ArrayList<>();
        List<ArticleDO.PollOption> pollOptions = new ArrayList<>();

        List<ArticleDO.ArticleSectionDO> articleSectionDOList = new ArrayList<>();

        String postImageURL = "";

        Date created_at = new Date();
        List<Date> updated_at = new ArrayList<>();

        //Calculated Values

        String creatorName="";
        String communityName="";
        ProfileDTOs.ProfileInfoComponentCO profileInfoComponentCO;
        List<ObjectId> otherPostsInWork = new ArrayList<>();
        List<String> otherPostsInWorkHex = new ArrayList<>();
    }

    /**
     * This CO is used to return data needed to render an article to an article component
     */
    @Data
    @NoArgsConstructor
    public static class ArticleTabComponentCO extends RepresentationModel<ArticleTabComponentCO> {

        String ACCESS_TYPE;

        //Direct Values

        String _id;
        ObjectId objectId;

        ObjectId creatorID;
        ObjectId communityID;

        int likes = 0;
        int dislikes = 0;
        List<ObjectId> userLikeIDs = new ArrayList<>();
        List<ObjectId> userDislikeIDs = new ArrayList<>();

        Boolean isDraft=true;
        String articleName="";
        String workName = "";

        List<String> tagList = new ArrayList<>();
        List<ArticleDO.Comment> comments = new ArrayList<>();
        List<ArticleDO.PollOption> pollOptions = new ArrayList<>();

        List<ArticleDO.ArticleSectionDO> articleSectionDOList = new ArrayList<>();

        String postImageURL = "";

        Date created_at = new Date();
        List<Date> updated_at = new ArrayList<>();

        //Calculated Values

        List<ArticleSectionCO> articleSectionCOList = new ArrayList<>();
        ProfileDTOs.ProfileInfoComponentCO profileInfoComponentCO;
        List<ObjectId> otherPostsInWork = new ArrayList<>();
        List<String> otherPostsInWorkHex = new ArrayList<>();
        String creatorName="";
        String communityName="";

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
        String imageID;
    }
}
