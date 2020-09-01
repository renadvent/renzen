package BackEndRewrite.CommandObjects.ContentCOs;

import BackEndRewrite.DomainObjects.Subsections.DiscussionSectionDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DiscussionSectionCO {
    ObjectId author;
    String content;

    String _id;

    Integer replyCount;
    //List<String> replyCOIDList;
    List<DiscussionSectionCO> discussionSectionCOList;
}
