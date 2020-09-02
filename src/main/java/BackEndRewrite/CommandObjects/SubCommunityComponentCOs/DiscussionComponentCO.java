package BackEndRewrite.CommandObjects.SubCommunityComponentCOs;

import BackEndRewrite.CommandObjects.ContentCOs.DiscussionSectionCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DiscussionComponentCO extends RepresentationModel<DiscussionComponentCO> {
    String _id;
    ObjectId objectId;

    List<DiscussionSectionCO> discussionSectionCOList = new ArrayList<>();

}
