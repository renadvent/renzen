package com.ren.renzen.CommandObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileDTOs {
    /***
     *
     */
    @Data
    @NoArgsConstructor
    public static class ProfileInfoComponentCO extends RepresentationModel<ProfileInfoComponentCO> {

        String ACCESS_TYPE;

        //---------------

        String _id;
        ObjectId objectId;
        String name;

        Integer numberOfArticles;
        Integer numberOfCommunities;
        Integer numberOfDrafts;

        List<ObjectId> articleIDList = new ArrayList<>();
        List<ObjectId> communityIDList = new ArrayList<>();

        List<ObjectId> articleDraftIDList = new ArrayList<>();

        List<String> workNames = new ArrayList<>();


    }

    /***
     *
     */

    @Data
    @NoArgsConstructor
    public static class ProfileTabComponentCO extends RepresentationModel<ProfileTabComponentCO> {

        //react uses this to know what component type/component render
        String ACCESS_TYPE;//Public or Private //normalized state regardless

        //----------------------------------

        String name;
        String _id;
        ObjectId objectId;

        Integer numberOfArticles;
        Integer numberOfDrafts;

        Integer numberOfCommunities;
        Integer numberOfDiscussionContentPosts;
        List<ObjectId> articleIDList = new ArrayList<>();
        List<ObjectId> communityIDList = new ArrayList<>();

        CollectionModel<ArticleDTOs.ArticleInfoComponentCO> articleDraftInfoComponentCOs;

        CollectionModel<ArticleDTOs.ArticleInfoComponentCO> articleInfoComponentCOS;
        CollectionModel<CommunityDTOs.CommunityInfoComponentCO> communityInfoComponentCOS;
        CollectionModel<ArticleDTOs.ArticleInfoComponentCO> articleBookmarksCM;

        List<ObjectId> articleDraftIDList = new ArrayList<>();

        List<String> screenshotLinks = new ArrayList<>();

        List<String> workNames = new ArrayList<>();


        //NEW
        List<String> originalLinks = new ArrayList<>();

    }
}
