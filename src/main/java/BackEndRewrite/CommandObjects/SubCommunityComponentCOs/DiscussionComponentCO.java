package BackEndRewrite.CommandObjects.SubCommunityComponentCOs;

import BackEndRewrite.CommandObjects.ContentCOs.DiscussionSectionCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DiscussionComponentCO extends RepresentationModel<DiscussionComponentCO> {

    //List<String> discussionContentIDList;
    ObjectId id;
    List<DiscussionSectionCO> discussionSectionCOList;

}
