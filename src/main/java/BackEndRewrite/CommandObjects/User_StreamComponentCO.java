package BackEndRewrite.CommandObjects;

import BackEndRewrite.DomainObjectBaseClasses.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class User_StreamComponentCO extends BaseEntity {
    String username;

    Integer numberOfArticles;
    Integer numberOfCommunities;
    Integer numberOfDiscussionContentPosts;

    List<String> articleIDList;
    List<String> communityIDList;
    List<String> discussionContentIDList;
}
