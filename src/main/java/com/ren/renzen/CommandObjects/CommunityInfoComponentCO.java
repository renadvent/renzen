package com.ren.renzen.CommandObjects;

import lombok.Data;
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
 * <p>
 * It is used on the Index Page and Profile Page
 */
@Data
@NoArgsConstructor
public class CommunityInfoComponentCO extends RepresentationModel<CommunityInfoComponentCO> {

    String ACCESS_TYPE;

    //---------------

    String _id;
    ObjectId objectId;
    String name;
    List<ProfileInfoComponentCO> profileInfoComponentCOList = new ArrayList<>();
    List<ArticleInfoComponentCO> articleInfoComponentCOList = new ArrayList<>();
}
