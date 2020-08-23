package BackEndRewrite.CommandObjects;

import java.util.List;

/**
 * This Command Object is used to return data to
 * React Application needed to render the Home Page of a Community
 */
public class Community_HomePageCO {

    /**
     * This List is used by the React Application to render the
     * names of the articles and to provide links to those articles
     */
    List<Article_StreamComponentCO> article_streamComponentCOList;
    //stream component

    Integer numberOfArticles;

    /**
     * This List is used by the React Application to render the
     * names of the members of this community and provide Links
     */
    List<User_StreamComponentCO> user_streamComponentCOList;
    Integer numberOfUsers;

    /**
     * This List is used by the React Application to render the
     * names of teh communities and provide Links
     */
    List<Community_StreamComponentCO> community_streamComponentCOList;
    Integer numberOfCommunities;

    /**
     * This Object is used to render the Community Discussion section
     * on the homepage
     */
    DiscussionComponentRenderCO discussionComponentRenderCO;

}
