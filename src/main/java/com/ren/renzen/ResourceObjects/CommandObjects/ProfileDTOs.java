package com.ren.renzen.ResourceObjects.CommandObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        String profilePictureLink;


        Set<ObjectId> articleIDList = new HashSet<>();
        Set<ObjectId> communityIDList = new HashSet<>();
        Set<ObjectId> articleDraftIDList = new HashSet<>();

        CollectionModel<ArticleDTOs.ArticleInfoComponentCO> articleDraftInfoComponentCOS;
        CollectionModel<ArticleDTOs.ArticleInfoComponentCO> articleInfoComponentCOS;
        CollectionModel<CommunityDTOs.CommunityInfoComponentCO> communityInfoComponentCOS;
        CollectionModel<ArticleDTOs.ArticleInfoComponentCO> articleBookmarksCM;

        Set<String> workNames = new HashSet<>();

        //Calculated Values

        Integer numberOfArticles;
        Integer numberOfDrafts;
        Integer numberOfCommunities;

    }
}
