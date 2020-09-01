package BackEndRewrite.CommandObjects.TabComponentCOs;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.CommunityStreamComponentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class ProfileTabComponentCO extends RepresentationModel<ProfileTabComponentCO> {
    String username;

    Integer numberOfArticles;
    Integer numberOfCommunities;
    Integer numberOfDiscussionContentPosts;

    List<ObjectId> articleIDList;
    List<ObjectId> communityIDList;
    List<ObjectId> discussionContentIDList;

    List<ArticleStreamComponentCO> articleHomePageCOList;
    List<CommunityStreamComponentCO> communityIndexPageCOList;
    //List<DiscussionInfoCO> discussionInfoCOList;
}
