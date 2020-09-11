package com.ren.renzen.DomainObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


import java.util.ArrayList;
import java.util.List;

/**
 * DO for discussion
 */
@Getter@Setter
@Document(collection = "Discussions")
@NoArgsConstructor
public class DiscussionDO {
    @Id
    ObjectId _id;
    List<DiscussionSectionDO> discussionSectionDOList = new ArrayList<>();
}
