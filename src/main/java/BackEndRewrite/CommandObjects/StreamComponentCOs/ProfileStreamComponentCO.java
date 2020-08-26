package BackEndRewrite.CommandObjects.StreamComponentCOs;

import BackEndRewrite.DomainObjectBaseClasses.BaseDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class ProfileStreamComponentCO extends BaseDomain {
    String username;

    Integer numberOfArticles;
    Integer numberOfCommunities;
    //Integer numberOfDiscussionContentPosts;

    List<String> articleIDList;
    List<String> communityIDList;

    //this may be broken... because content don't have their own repo anymore
    //List<String> discussionContentIDList;
    //List<String> discussionsPostedIn;
}
