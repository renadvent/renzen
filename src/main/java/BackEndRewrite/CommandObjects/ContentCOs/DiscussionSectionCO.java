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
    String _id;
    ObjectId objectId;

    ObjectId authorID;
    String content;
    Integer replyCount;
    List<DiscussionSectionCO> discussionSectionCOList;
}
