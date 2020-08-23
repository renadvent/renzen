package BackEndRewrite.CommandObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class UserProfileRenderCO {
    String username;

    Integer numberOfArticles;
    Integer numberOfCommunities;
    Integer numberOfDiscussionContentPosts;

    List<String> articleIDList;
    List<String> communityIDList;
    List<String> discussionContentIDList;

    List<Article_StreamComponentCO> articleHomePageCOList;
    List<Community_StreamComponentCO> communityIndexPageCOList;
    List<DiscussionInfoCO> discussionInfoCOList;
}
