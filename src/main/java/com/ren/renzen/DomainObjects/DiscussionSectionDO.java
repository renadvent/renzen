package com.ren.renzen.DomainObjects;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DO for discussion content/section
 * and replies
 */
@Data
@NoArgsConstructor
public class DiscussionSectionDO {
    List<DiscussionSectionDO> replies;
}
