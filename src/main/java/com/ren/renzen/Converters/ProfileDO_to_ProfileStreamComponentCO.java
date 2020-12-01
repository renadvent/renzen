package com.ren.renzen.Converters;

import com.ren.renzen.CommandObjects.ProfileDTOs;
import com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER_SUPPORT;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileDO_to_ProfileStreamComponentCO extends DOMAIN_VIEW_CONVERTER_SUPPORT<ProfileDO, ProfileDTOs.ProfileInfoComponentCO> {

    final UserService userService;

    @Autowired
    public ProfileDO_to_ProfileStreamComponentCO(UserService userService) {
        this.userService = userService;
    }

    public ProfileDTOs.ProfileInfoComponentCO common(ProfileDO source) {

        final ProfileDTOs.ProfileInfoComponentCO co = new ProfileDTOs.ProfileInfoComponentCO();

        co.setArticleDraftIDList(source.getArticleDraftIDList());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setName(source.getUsername());
        co.setNumberOfArticles(source.getArticleIDList().size());
        co.setNumberOfCommunities(source.getCommunityIDList().size());
        co.setWorkNames(source.getWorkNames());

        return co;

    }

    @Override
    public ProfileDTOs.ProfileInfoComponentCO convertDomainToPublicView(ProfileDO source) {

        var co = common(source);
        return co;
    }

    @Override
    public ProfileDTOs.ProfileInfoComponentCO convertDomainToFullView(ProfileDO source) {

        var co = common(source);
        return co;
    }
}
