package BackEndRewrite.DomainObjects.Subsections;


import BackEndRewrite.DomainObjectBaseClasses.BaseSection;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DO for discussion content/section
 * and replies
 */
@Getter@Setter
public class DiscussionSectionDO extends BaseSection {
    /**
     * replies
     */
    List<DiscussionSectionDO> replies;
}
