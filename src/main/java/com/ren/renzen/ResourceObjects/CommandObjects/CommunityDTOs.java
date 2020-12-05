package com.ren.renzen.ResourceObjects.CommandObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class CommunityDTOs {

    @Data
    @NoArgsConstructor
    public static class CommunityInfoComponentCO extends RepresentationModel<CommunityInfoComponentCO> {

        String ACCESS_TYPE;

        //Direct Values

        String _id;
        ObjectId objectId;
        String name;

        //Calculated Values

        List<ProfileDTOs.ProfileInfoComponentCO> profileInfoComponentCOList = new ArrayList<>();
        List<ArticleDTOs.ArticleInfoComponentCO> articleInfoComponentCOList = new ArrayList<>();
    }


    @Data
    @NoArgsConstructor
    public static class CommunityTabComponentCO extends RepresentationModel<CommunityTabComponentCO> {

        String ACCESS_TYPE;

        //Direct Values

        String _id;
        ObjectId objectId;
        String name;

        //Calculated Values

        CollectionModel<ArticleDTOs.ArticleInfoComponentCO> articleInfoComponentCOS;
        CollectionModel<ProfileDTOs.ProfileInfoComponentCO> profileInfoComponentCOS;

        Integer numberOfUsers;
        Integer numberOfArticles;

    }
}
