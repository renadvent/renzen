package BackEndRewrite.CommandObjects;

import java.util.List;

/**
 * This Command Object is used to return data to
 * React Application needed to render the main index page at (/index)
 */

public class IndexPageCO {
    List<Article_StreamComponentCO> article_streamComponentCOList;
    List<User_StreamComponentCO> user_streamComponentCOList;
    List<Community_StreamComponentCO> community_streamComponentCOList;
}
