package BackEndRewrite.DomainObjects.Subsections;


import BackEndRewrite.DomainObjectBaseClasses.BaseContent;

import java.util.List;

/**
 * DO for discussion content/section
 * and replies
 */

public class DiscussionSectionDO extends BaseContent {
    List<DiscussionSectionDO> replies;
}
