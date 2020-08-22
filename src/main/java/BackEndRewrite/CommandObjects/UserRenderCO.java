package BackEndRewrite.CommandObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class UserRenderCO {
    String username;

    List<String> articleIDList;
    List<String> communityIDList;
    List<String> discussionContentIDList;

    List<ArticleInfoCO> articleInfoCOList;
    List<CommunityInfoCO> communityInfoCOList;
    List<DiscussionInfoCO> discussionInfoCOList;
}
