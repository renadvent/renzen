package com.ren.renzen.ResourceObjects.CommandObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileDTOs {

    @Data
    @NoArgsConstructor
    public static class ProfileInfoComponentCO extends RepresentationModel<ProfileInfoComponentCO> {

        String ACCESS_TYPE;

        //Direct Values
        String _id;
        ObjectId objectId;
        String name;
        String profilePictureLink;

    }

    @Data
    @NoArgsConstructor
    public static class ProfileTabComponentCO extends RepresentationModel<ProfileTabComponentCO> {

        String ACCESS_TYPE;

        //Direct Values
        String name;
        String _id;
        ObjectId objectId;

        List<ObjectId> articleIDList = new ArrayList<>();
        List<ObjectId> communityIDList = new ArrayList<>();
        List<ObjectId> articleDraftIDList = new ArrayList<>();

        CollectionModel<ArticleDTOs.ArticleInfoComponentCO> articleDraftInfoComponentCOS;
        CollectionModel<ArticleDTOs.ArticleInfoComponentCO> articleInfoComponentCOS;
        CollectionModel<CommunityDTOs.CommunityInfoComponentCO> communityInfoComponentCOS;
        CollectionModel<ArticleDTOs.ArticleInfoComponentCO> articleBookmarksCM;

        List<String> workNames = new ArrayList<>();

        //Calculated Values

        Integer numberOfArticles;
        Integer numberOfDrafts;
        Integer numberOfCommunities;

    }
}
