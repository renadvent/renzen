package BackEndRewrite.DomainObjects;


import BackEndRewrite.DomainObjectBaseClasses.BaseContent;

import java.util.List;

/**
 * DO for discussion content/section
 * and replies
 */

public class DiscussionContentDO extends BaseContent {
    List<DiscussionContentDO> replies;
}
