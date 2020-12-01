package com.ren.renzen.CommandObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class CommunityDTOs {
    /**
     * This CO is used to return data needed to LIST a community (metadata)
     * Used where knowledge of the article is necessary, but not the contents
     * <p>
     * It is used on the Index Page and Profile Page
     */
    @Data
    @NoArgsConstructor
    public static class CommunityInfoComponentCO extends RepresentationModel<CommunityInfoComponentCO> {

        String ACCESS_TYPE;

        //---------------

        String _id;
        ObjectId objectId;
        String name;
        List<ProfileDTOs.ProfileInfoComponentCO> profileInfoComponentCOList = new ArrayList<>();
        List<ArticleDTOs.ArticleInfoComponentCO> articleInfoComponentCOList = new ArrayList<>();
    }

    /**
     * This CO is used to return data needed to render an article to community component
     */
    @Data
    @NoArgsConstructor
    public static class CommunityTabComponentCO extends RepresentationModel<CommunityTabComponentCO> {

        String ACCESS_TYPE;

        //---------------

        String _id;
        ObjectId objectId;
        String name;

        /**
         * This List is used by the React Application to render the
         * names of the articles and to provide links to those articles
         */
        CollectionModel<ArticleDTOs.ArticleInfoComponentCO> articleInfoComponentCOS;

        Integer numberOfArticles;
        /**
         * This List is used by the React Application to render the
         * names of the members of this community and provide Links
         */

        CollectionModel<ProfileDTOs.ProfileInfoComponentCO> profileInfoComponentCOS;
        Integer numberOfUsers;
        /**
         * This Object is used to render the Community Discussion section
         * on the homepage
         */
    }
}
