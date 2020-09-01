package BackEndRewrite.DomainObjects;

import BackEndRewrite.DomainObjects.Subsections.DiscussionSectionDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.MongoId;


import java.util.ArrayList;
import java.util.List;

/**
 * DO for discussion
 */
@Getter@Setter
@Document(collection = "Discussions")
@NoArgsConstructor
public class DiscussionDO {

    @org.springframework.data.annotation.Id
    String Id;
    List<DiscussionSectionDO> discussionSectionDOList = new ArrayList<>();
}
