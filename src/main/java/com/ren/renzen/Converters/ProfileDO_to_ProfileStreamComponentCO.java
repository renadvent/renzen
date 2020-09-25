package com.ren.renzen.Converters;

import com.mongodb.lang.Nullable;
import com.ren.renzen.CommandObjects.ProfileStreamComponentCO;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.Services.Interfaces.UserService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileDO_to_ProfileStreamComponentCO implements Converter<ProfileDO, ProfileStreamComponentCO> {

    final UserService userService;

    @Autowired
    public ProfileDO_to_ProfileStreamComponentCO(UserService userService) {
        this.userService = userService;
    }

    @Synchronized
    @Nullable
    @Override
    public ProfileStreamComponentCO convert(ProfileDO source) {

        final ProfileStreamComponentCO co = new ProfileStreamComponentCO();

        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setName(source.getUsername());
        co.setNumberOfArticles(source.getArticleIDList().size());
        co.setNumberOfCommunities(source.getCommunityIDList().size());

        return co;
    }
}
