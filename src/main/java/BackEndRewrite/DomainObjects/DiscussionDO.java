package BackEndRewrite.DomainObjects;

import BackEndRewrite.DomainObjects.DomainObjectBaseClasses.BaseDomain;
import BackEndRewrite.DomainObjects.Subsections.DiscussionSectionDO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DO for discussion
 */
@Getter@Setter
public class DiscussionDO extends BaseDomain {
    List<DiscussionSectionDO> discussionSectionDOList;
}
