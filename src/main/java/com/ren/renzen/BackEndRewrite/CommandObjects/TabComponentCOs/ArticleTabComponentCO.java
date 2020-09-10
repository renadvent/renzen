package com.ren.renzen.BackEndRewrite.CommandObjects.TabComponentCOs;

import com.ren.renzen.BackEndRewrite.CommandObjects.ContentCOs.ArticleSectionCO;
import com.ren.renzen.BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This CO is used to return data needed to render ar article to an article component
 */
@Getter
@Setter
@NoArgsConstructor
public class ArticleTabComponentCO extends RepresentationModel<ArticleTabComponentCO> {
    String _id;
    ObjectId objectId;
    String userName;

    String name;
    String description;
    ObjectId userID;
    ProfileStreamComponentCO user_streamComponentCO;
    ObjectId discussionID;
    List<ArticleSectionCO> articleSectionCOList = new ArrayList<>();
}
