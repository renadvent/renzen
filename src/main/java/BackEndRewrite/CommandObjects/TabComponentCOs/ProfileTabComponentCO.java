package BackEndRewrite.CommandObjects.TabComponentCOs;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.CommunityStreamComponentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class ProfileTabComponentCO extends RepresentationModel<ProfileTabComponentCO> {
    String username;

    Integer numberOfArticles;
    Integer numberOfCommunities;
    Integer numberOfDiscussionContentPosts;

    List<String> articleIDList;
    List<String> communityIDList;
    List<String> discussionContentIDList;

    List<ArticleStreamComponentCO> articleHomePageCOList;
    List<CommunityStreamComponentCO> communityIndexPageCOList;
    //List<DiscussionInfoCO> discussionInfoCOList;
}
