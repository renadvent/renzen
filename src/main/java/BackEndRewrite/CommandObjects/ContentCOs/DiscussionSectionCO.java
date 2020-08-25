package BackEndRewrite.CommandObjects.ContentCOs;

import BackEndRewrite.DomainObjects.Subsections.DiscussionSectionDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DiscussionSectionCO {
    String author;
    String content;
    String id;

    Integer replyCount;
    //List<String> replyCOIDList;
    List<DiscussionSectionCO> discussionSectionCOList;
}
