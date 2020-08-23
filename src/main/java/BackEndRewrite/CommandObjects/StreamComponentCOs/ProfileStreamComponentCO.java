package BackEndRewrite.CommandObjects.StreamComponentCOs;

import BackEndRewrite.DomainObjectBaseClasses.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class ProfileStreamComponentCO extends BaseEntity {
    String username;

    Integer numberOfArticles;
    Integer numberOfCommunities;
    Integer numberOfDiscussionContentPosts;

    List<String> articleIDList;
    List<String> communityIDList;
    List<String> discussionContentIDList;
}
