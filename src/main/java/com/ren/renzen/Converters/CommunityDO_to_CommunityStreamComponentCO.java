package com.ren.renzen.Converters;

import com.ren.renzen.CommandObjects.CommunityStreamComponentCO;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommunityDO_to_CommunityStreamComponentCO implements Converter<CommunityDO, CommunityStreamComponentCO> {

    final ArticleDO_to_ArticleStreamComponentCO testConverter;
    final ProfileDO_to_ProfileStreamComponentCO ProfileConverter;

    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

    @Autowired
    public CommunityDO_to_CommunityStreamComponentCO(ArticleDO_to_ArticleStreamComponentCO testConverter, ProfileDO_to_ProfileStreamComponentCO profileConverter, UserService userService, ArticleService articleService, CommunityService communityService) {
        this.testConverter = testConverter;
        ProfileConverter = profileConverter;
        this.userService = userService;
        this.articleService = articleService;
        this.communityService = communityService;
    }

    /**
     * allows for multiple conversions
     *
     * @param sourceList
     * @return
     */
    @Synchronized
    @Nullable
    public List<CommunityStreamComponentCO> convert(Iterable<CommunityDO> sourceList) {
        ArrayList<CommunityStreamComponentCO> communityStreamComponentCOList = new ArrayList<CommunityStreamComponentCO>();
        for (CommunityDO communityDO : sourceList) {
            communityStreamComponentCOList.add(convert(communityDO));
        }
        return communityStreamComponentCOList;
    }

    @Synchronized
    @Nullable
    public List<CommunityStreamComponentCO> convert(List<ObjectId> sourceList){
        return sourceList.stream().map(e->{
            return convert(communityService.findBy_id(e));
        }).collect(Collectors.toList());
    }

    @Synchronized
    @Nullable
    @Override
    public CommunityStreamComponentCO convert(CommunityDO source) {

        CommunityStreamComponentCO co = new CommunityStreamComponentCO();

        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setName(source.getName());

        //does not return ProfileStreamComponentCOList or ArticleStreamComponentCOList
        //to cut down on database searches and unused data by client

        return co;
    }
}