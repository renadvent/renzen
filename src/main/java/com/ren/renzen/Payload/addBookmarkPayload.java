package com.ren.renzen.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
public class addBookmarkPayload {

    //ObjectId userId;
    ObjectId articleId;
}
