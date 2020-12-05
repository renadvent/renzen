package com.ren.renzen.CommandObjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This Command Object is used to return data to
 * React Application needed to render the main index page at (/index)
 */
@Data
@NoArgsConstructor
public class HomeTabComponentCO extends RepresentationModel<HomeTabComponentCO> {

    String ACCESS_TYPE;

    //---------------

    String _id;
    ObjectId objectId;
    List<ArticleDTOs.ArticleInfoComponentCO> articleInfoComponentCOList = new ArrayList<>();
    List<ProfileDTOs.ProfileInfoComponentCO> profileInfoComponentCOList = new ArrayList<>();
    List<CommunityDTOs.CommunityInfoComponentCO> communityInfoComponentCOList = new ArrayList<>();
}
