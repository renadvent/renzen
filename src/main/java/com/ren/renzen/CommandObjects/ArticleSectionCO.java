package com.ren.renzen.CommandObjects;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

/**
 * This CO is used to return a section of an article for rendering
 */
@Data
@NoArgsConstructor
public class ArticleSectionCO {

    String ACCESS_TYPE;

    //---------------

    String _id;
    ObjectId objectId;
    String header;
    String body;
    ObjectId authorID;
    Integer upvotes;
    Integer downvotes;
    String imageID;
}
