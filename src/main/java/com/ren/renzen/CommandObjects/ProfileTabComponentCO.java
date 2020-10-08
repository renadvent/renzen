package com.ren.renzen.CommandObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/***
 *
 */

@Data
@NoArgsConstructor
public class ProfileTabComponentCO extends RepresentationModel<ProfileTabComponentCO> {

    //react uses this to know what component type/component render
    String ACCESS_TYPE;//Public or Private //normalized state regardless

    //----------------------------------

    String name;
    String _id;
    ObjectId objectId;
    Integer numberOfArticles;
    Integer numberOfCommunities;
    Integer numberOfDiscussionContentPosts;
    List<ObjectId> articleIDList = new ArrayList<>();
    List<ObjectId> communityIDList = new ArrayList<>();
    List<ObjectId> discussionContentIDList = new ArrayList<>();
    CollectionModel<ArticleInfoComponentCO> articleStreamComponentCOS;
    CollectionModel<CommunityInfoComponentCO> communityInfoComponentCOS;
    CollectionModel<ArticleInfoComponentCO> articleBookmarksCM;

    List<String> screenshotLinks = new ArrayList<>();

}
