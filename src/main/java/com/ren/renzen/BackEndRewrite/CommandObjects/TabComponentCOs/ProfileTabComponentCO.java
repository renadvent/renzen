package com.ren.renzen.BackEndRewrite.CommandObjects.TabComponentCOs;

import com.ren.renzen.BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import com.ren.renzen.BackEndRewrite.CommandObjects.StreamComponentCOs.CommunityStreamComponentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class ProfileTabComponentCO extends RepresentationModel<ProfileTabComponentCO> {
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
    //List<DiscussionInfoCO> discussionInfoCOList;
}
