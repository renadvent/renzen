package com.ren.renzen.CommandObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

/**
 * This CO is used to return data needed to render an article to community component
 */
@Data
@NoArgsConstructor
public class CommunityTabComponentCO extends RepresentationModel<CommunityTabComponentCO> {

    String ACCESS_TYPE;

    //---------------

    String _id;
    ObjectId objectId;
    String name;

    /**
     * This List is used by the React Application to render the
     * names of the articles and to provide links to those articles
     */
    CollectionModel<ArticleInfoComponentCO> articleInfoComponentCOS;

    Integer numberOfArticles;
    /**
     * This List is used by the React Application to render the
     * names of the members of this community and provide Links
     */

    CollectionModel<ProfileInfoComponentCO> profileInfoComponentCOS;
    Integer numberOfUsers;
    /**
     * This Object is used to render the Community Discussion section
     * on the homepage
     */
}
