package com.ren.renzen.Payload;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class addBookmarkPayload {

    ObjectId userId;
    ObjectId articleId;
}
