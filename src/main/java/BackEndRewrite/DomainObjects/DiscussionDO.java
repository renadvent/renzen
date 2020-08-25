package BackEndRewrite.DomainObjects;

import BackEndRewrite.DomainObjectBaseClasses.BaseEntity;
import BackEndRewrite.DomainObjects.Subsections.DiscussionSectionDO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DO for discussion
 */
@Getter@Setter
public class DiscussionDO extends BaseEntity {
    List<DiscussionSectionDO> discussionSectionDOList;
}
