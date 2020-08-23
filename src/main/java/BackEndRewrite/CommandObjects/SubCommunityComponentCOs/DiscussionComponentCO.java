package BackEndRewrite.CommandObjects.SubCommunityComponentCOs;

import BackEndRewrite.CommandObjects.ContentCOs.DiscussionContentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DiscussionComponentCO {

    List<String> discussionContentIDList;

    List<DiscussionContentCO> discussionContentCOList;

}
