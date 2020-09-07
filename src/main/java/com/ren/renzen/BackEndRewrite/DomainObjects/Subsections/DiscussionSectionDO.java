package com.ren.renzen.BackEndRewrite.DomainObjects.Subsections;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DO for discussion content/section
 * and replies
 */
@Getter@Setter
public class DiscussionSectionDO {
    /**
     * replies
     */
    List<DiscussionSectionDO> replies;



}
