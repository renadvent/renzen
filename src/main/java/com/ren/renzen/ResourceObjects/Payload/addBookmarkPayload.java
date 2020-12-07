package com.ren.renzen.ResourceObjects.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
public class addBookmarkPayload {
    ObjectId articleId;
}
