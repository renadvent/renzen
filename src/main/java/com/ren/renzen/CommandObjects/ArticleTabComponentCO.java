package com.ren.renzen.CommandObjects;

import com.ren.renzen.DomainObjects.ArticleDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This CO is used to return data needed to render an article to an article component
 */
@Data
@NoArgsConstructor
public class ArticleTabComponentCO extends RepresentationModel<ArticleTabComponentCO> {

    String ACCESS_TYPE;

    //---------------

    String _id;
    ObjectId objectId;
    String userName;
    String name;
    String description;
    ObjectId userID;
    ProfileInfoComponentCO profileInfoComponentCO;
    ObjectId discussionID;
    List<ArticleSectionCO> articleSectionCOList = new ArrayList<>();

    int likes;
    int dislikes;

    //new

    String image="";
    List<ArticleDO.Comment> comments = new ArrayList<>();


    //NEW
    String originalImageLink="";


}
