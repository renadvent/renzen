package com.ren.renzen.CommandObjects;

import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileTabComponentSecurity extends RepresentationModel<ProfileTabComponentCO> {

    //can include personal details not available in tabs

    String name;
    String _id;
    ObjectId objectId;
    Integer numberOfArticles;
    Integer numberOfCommunities;
    Integer numberOfDiscussionContentPosts;
    List<ObjectId> articleIDList = new ArrayList<>();
    List<ObjectId> communityIDList = new ArrayList<>();
    List<ObjectId> discussionContentIDList = new ArrayList<>();
    CollectionModel<ArticleStreamComponentCO> articleHomePageCOList;
    CollectionModel<CommunityStreamComponentCO> communityStreamComponentCOList;
    CollectionModel<ArticleStreamComponentCO> articleBookmarksCM;

    List<String> screenshotLinks = new ArrayList<>();
}
