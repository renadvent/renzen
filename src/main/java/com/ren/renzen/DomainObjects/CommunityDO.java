package com.ren.renzen.DomainObjects;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * DO for community
 */
@Getter@Setter
@Document(collection = "Communities")
public class CommunityDO{

    @Id
    ObjectId _id;
    String name;
    ObjectId creatorID;
    List<ObjectId> profileDOList= new ArrayList<>();
    List<ObjectId> articleDOList= new ArrayList<>();
    ObjectId discussionID;

    public CommunityDO(String name,ObjectId creatorID){
        this.name=name;
        this.creatorID=creatorID;
        this.getProfileDOList().add(creatorID);
    }

}
