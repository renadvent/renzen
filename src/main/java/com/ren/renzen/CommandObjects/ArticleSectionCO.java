package com.ren.renzen.CommandObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

/**
 * This CO is used to return a section of an article for rendering
 */
@Getter
@Setter
@NoArgsConstructor
public class ArticleSectionCO {
    String _id;
    ObjectId objectId;
    String header;
    String body;
    ObjectId authorID;
    Integer upvotes;
    Integer downvotes;
}
