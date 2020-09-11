package com.ren.renzen.CommandObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This CO is used to return data needed to LIST a community (metadata)
 * Used where knowledge of the article is necessary, but not the contents
 *
 * It is used on the Index Page and Profile Page
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class CommunityStreamComponentCO extends RepresentationModel<CommunityStreamComponentCO> {
    String _id;
    ObjectId objectId;
    String name;
    List<ProfileStreamComponentCO> profileStreamComponentCOList = new ArrayList<>();
    List<ArticleStreamComponentCO> articleStreamComponentCOList = new ArrayList<>();
}
