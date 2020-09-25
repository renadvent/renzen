package com.ren.renzen.DomainObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * DO for discussion
 */
@Data
@Document(collection = "Discussions")
@NoArgsConstructor
public class DiscussionDO {
    @Id
    ObjectId _id;
    List<DiscussionSectionDO> discussionSectionDOList = new ArrayList<>();
}
