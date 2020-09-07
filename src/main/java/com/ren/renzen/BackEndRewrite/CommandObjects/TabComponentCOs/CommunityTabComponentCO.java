package com.ren.renzen.BackEndRewrite.CommandObjects.TabComponentCOs;

import com.ren.renzen.BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import com.ren.renzen.BackEndRewrite.CommandObjects.SubCommunityComponentCOs.DiscussionComponentCO;
import com.ren.renzen.BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This Command Object is used to return data to
 * React Application needed to render the Home Page of a Community
 */
@Getter
@Setter
@NoArgsConstructor
public class CommunityTabComponentCO extends RepresentationModel<CommunityTabComponentCO> {

    String _id;
    ObjectId objectId;
    String name;

    /**
     * This List is used by the React Application to render the
     * names of the articles and to provide links to those articles
     */
    List<ArticleStreamComponentCO> article_Article_streamComponentCOList = new ArrayList<>();

    Integer numberOfArticles;
    /**
     * This List is used by the React Application to render the
     * names of the members of this community and provide Links
     */

    CollectionModel
            <ProfileStreamComponentCO> user_streamComponentCOList;
    //List<ProfileStreamComponentCO> user_streamComponentCOList = new ArrayList<>();
    Integer numberOfUsers;
    /**
     * This Object is used to render the Community Discussion section
     * on the homepage
     */
    DiscussionComponentCO discussionDiscussionComponentCO;

}
