package com.ren.renzen.CommandObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class ArticleInfoExposedCO {

    //data direct from domain object
    String authorID;
    String communityID;
    List<ArticleContent> contents = new ArrayList<>();

    //transformed data
    UserInfoExposedCO authorInfo;
    CommunityInfoExposedCO communityInfo;

    //aggregated data

}
