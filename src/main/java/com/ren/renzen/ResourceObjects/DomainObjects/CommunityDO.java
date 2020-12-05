package com.ren.renzen.ResourceObjects.DomainObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DO for community
 */
@Data
@NoArgsConstructor
@Document(collection = "Communities")
public class CommunityDO {

    @MongoId
    ObjectId _id;
    String name;
    ObjectId creatorID;
    String creatorName;

    Date created_at = new Date();
    List<Date> updated_at = new ArrayList<>();

    List<ObjectId> profileDOList = new ArrayList<>();
    List<ObjectId> articleDOList = new ArrayList<>();

    ObjectId discussionID;
}
