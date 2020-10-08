package com.ren.renzen.DomainObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

/**
 * DO for community
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Communities")
public class CommunityDO {

    @MongoId
    ObjectId _id;
    String name;
    ObjectId creatorID;
    String creatorName;

    //public community view settings
    boolean communityIsPublic = true;

    List<ObjectId> profileDOList = new ArrayList<>();
    List<ObjectId> articleDOList = new ArrayList<>();

    ObjectId discussionID;

    public CommunityDO(String name, ObjectId creatorID) {
        this.name = name;
        this.creatorID = creatorID;
        this.getProfileDOList().add(creatorID);

        this.communityIsPublic=true;
    }

}
