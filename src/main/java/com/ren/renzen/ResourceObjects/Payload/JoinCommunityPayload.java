package com.ren.renzen.ResourceObjects.Payload;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class JoinCommunityPayload {
    //ObjectId userId;
    ObjectId communityId;
}
