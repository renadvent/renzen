package com.ren.renzen.BackEndRewrite.Converters;

import com.ren.renzen.BackEndRewrite.CommandObjects.ProfileStreamComponentCO;
import com.ren.renzen.BackEndRewrite.DomainObjects.ProfileDO;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.UserService;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ProfileDO_to_ProfileStreamComponentCO implements Converter<ProfileDO,ProfileStreamComponentCO> {


    final UserService userService;

    @Autowired
    public ProfileDO_to_ProfileStreamComponentCO(UserService userService) {
        this.userService = userService;
    }

    @Synchronized
    @Nullable
    public List<ProfileStreamComponentCO> convert(List<ObjectId> sourceList){

        return sourceList.stream().map(e->{
            return convert(userService.findBy_id(e));
        }).collect(Collectors.toList());

    }

    @Synchronized@Nullable
    public List<ProfileStreamComponentCO> convert(Iterable<ProfileDO> sourceIterable){
        return StreamSupport.stream(sourceIterable.spliterator(),false)
                .map(this::convert).collect(Collectors.toList());
    }


    @Synchronized
    @Nullable
    @Override
    public ProfileStreamComponentCO convert(ProfileDO source){

        final ProfileStreamComponentCO co = new ProfileStreamComponentCO();

        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());

        co.setName(source.getUsername());
        co.setNumberOfArticles(source.getArticleIDList().size());
        co.setNumberOfCommunities(source.getCommunityIDList().size());

//        co.setArticleIDList(source.getArticleIDList());
//        co.setCommunityIDList(source.getCommunityIDList());

        return co;

    }
}
