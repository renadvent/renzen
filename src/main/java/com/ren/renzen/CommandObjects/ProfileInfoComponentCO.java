package com.ren.renzen.CommandObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/***
 *
 */
@Data
@NoArgsConstructor
public class ProfileInfoComponentCO extends RepresentationModel<ProfileInfoComponentCO> {

    String ACCESS_TYPE;

    //---------------

    String _id;
    ObjectId objectId;
    String name;
    Integer numberOfArticles;
    Integer numberOfCommunities;
    List<ObjectId> articleIDList = new ArrayList<>();
    List<ObjectId> communityIDList = new ArrayList<>();
}
