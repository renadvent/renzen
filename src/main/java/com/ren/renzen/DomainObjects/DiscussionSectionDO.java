package com.ren.renzen.DomainObjects;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DO for discussion content/section
 * and replies
 */
@Getter@Setter
public class DiscussionSectionDO {
    List<DiscussionSectionDO> replies;
}
