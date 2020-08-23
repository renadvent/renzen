package BackEndRewrite.CommandObjects;

import java.util.List;

/**
 * This CO is used to return data needed to render ar article to an article component
 */
public class Article_ArticleComponentCO {

    String name;
    String description;
    String id;

    String userID;
    User_StreamComponentCO user_streamComponentCO;

    String discussionID;
    DiscussionInfoCO discussionInfoCO;

    List<String> articleContentIDList;
    List<ArticleContentCO> articleContentCOList;

}
