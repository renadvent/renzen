package BackEndRewrite.CommandObjects.TabComponentCOs;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.CommunityStreamComponentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class ProfileTabComponentCO extends RepresentationModel<ProfileTabComponentCO> {
    String username;

    String _id;

    Integer numberOfArticles;
    Integer numberOfCommunities;
    Integer numberOfDiscussionContentPosts;

    List<ObjectId> articleIDList = new ArrayList<>();
    List<ObjectId> communityIDList = new ArrayList<>();
    List<ObjectId> discussionContentIDList = new ArrayList<>();

    List<ArticleStreamComponentCO> articleHomePageCOList = new ArrayList<>();
    List<CommunityStreamComponentCO> communityIndexPageCOList = new ArrayList<>();
    //List<DiscussionInfoCO> discussionInfoCOList;
}
