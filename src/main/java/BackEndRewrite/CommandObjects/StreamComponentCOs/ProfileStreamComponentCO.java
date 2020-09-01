package BackEndRewrite.CommandObjects.StreamComponentCOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class ProfileStreamComponentCO extends RepresentationModel<ProfileStreamComponentCO> {

    @Id
    String _id;

    String username;

    Integer numberOfArticles;
    Integer numberOfCommunities;
    //Integer numberOfDiscussionContentPosts;

    List<ObjectId> articleIDList;
    List<ObjectId> communityIDList;

    //this may be broken... because content don't have their own repo anymore
    //List<String> discussionContentIDList;
    //List<String> discussionsPostedIn;
}
