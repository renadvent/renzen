package BackEndRewrite.DomainObjects;

import BackEndRewrite.DomainObjects.DomainObjectBaseClasses.BaseDomain;
import BackEndRewrite.DomainObjects.Subsections.DiscussionSectionDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * DO for discussion
 */
@Getter@Setter
@Document(collection = "Disucssions")
@NoArgsConstructor
public class DiscussionDO extends BaseDomain {
    List<DiscussionSectionDO> discussionSectionDOList;
}
