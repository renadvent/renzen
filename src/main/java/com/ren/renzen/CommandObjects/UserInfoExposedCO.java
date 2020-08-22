package com.ren.renzen.CommandObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class UserInfoExposedCO {

    //data direct from domain object
    String name;
    List<String> articleIDs=new ArrayList<>();
    List<String> communityIDs=new ArrayList<>();

    //transformed data
    List<ArticleInfoExposedCO> articleCOs=new ArrayList<>();
    List<CommunityInfoExposedCO> communityCOs=new ArrayList<>();

    //aggregated data
    //example articles per community

}
