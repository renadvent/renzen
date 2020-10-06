package com.ren.renzen.DomainObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * DO for community
 */
@Data
@NoArgsConstructor
@Document(collection = "Communities")
public class CommunityDO {

    @Id
    ObjectId _id;
    String name;
    ObjectId creatorID;
    List<ObjectId> profileDOList = new ArrayList<>();
    List<ObjectId> articleDOList = new ArrayList<>();
    ObjectId discussionID;

    String creatorName;

    public CommunityDO(String name, ObjectId creatorID) {
        this.name = name;
        this.creatorID = creatorID;
        this.getProfileDOList().add(creatorID);
    }

}
