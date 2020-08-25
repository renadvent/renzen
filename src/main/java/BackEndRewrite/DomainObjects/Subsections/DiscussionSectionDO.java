package BackEndRewrite.DomainObjects.Subsections;


import BackEndRewrite.DomainObjectBaseClasses.BaseContent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DO for discussion content/section
 * and replies
 */
@Getter@Setter
public class DiscussionSectionDO extends BaseContent {
    /**
     * replies
     */
    List<DiscussionSectionDO> replies;
}
