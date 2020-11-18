package com.ren.renzen.CommandObjects;

import com.ren.renzen.DomainObjects.ArticleDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This CO is used to return data needed to LIST an article (metadata)
 * Used where knowledge of the article is necessary, but not the contents
 * <p>
 * It is used on the Index Page, the Profile Page, and Community Page
 */
@Data
@NoArgsConstructor
public class ArticleInfoComponentCO extends RepresentationModel<ArticleInfoComponentCO> {

    String ACCESS_TYPE;

    //---------------

    String _id;
    ObjectId objectId;
    String name;
    String description;
    String authorID;
    String authorName;
    ProfileInfoComponentCO profileInfoComponentCO;

    //NEW
    List<String> tagList = new ArrayList<>();
    String postText="";
    String postType="none";
    List<ArticleDO.Comment> comments = new ArrayList<>();
    List<ArticleDO.PollOption> pollOptions = new ArrayList<>();
    List<ObjectId> imageIDs = new ArrayList<>();

    List<ObjectId> otherPostsInWork=new ArrayList<>();
    List<String> otherPostsInWorkHex=new ArrayList<>();


    String workName="";


    String image="";

    //NEW
    String originalImageLink="";



    int likes;
    int dislikes;
}
