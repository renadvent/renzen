package BackEndRewrite.CommandObjects.TabComponentCOs;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.CommunityStreamComponentCO;
import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.DiscussionComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * This Command Object is used to return data to
 * React Application needed to render the Home Page of a Community
 */
@Getter
@Setter
@NoArgsConstructor
public class CommunityTabComponentCO {

    /**
     * This List is used by the React Application to render the
     * names of the articles and to provide links to those articles
     */
    List<ArticleStreamComponentCO> article_Article_streamComponentCOList;
    //stream component

    Integer numberOfArticles;

    /**
     * This List is used by the React Application to render the
     * names of the members of this community and provide Links
     */
    List<ProfileStreamComponentCO> user_streamComponentCOList;
    Integer numberOfUsers;

    /**
     * This List is used by the React Application to render the
     * names of teh communities and provide Links
     */
    List<CommunityStreamComponentCO> community_streamComponentCOList;
    Integer numberOfCommunities;

    /**
     * This Object is used to render the Community Discussion section
     * on the homepage
     */
    DiscussionComponentCO discussionDiscussionComponentCO;

}
