package com.ren.renzen.Converters;

import com.ren.renzen.CommandObjects.ArticleStreamComponentCO;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.Repositories.ArticleRepository;
import com.ren.renzen.Repositories.UserRepository;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ArticleDO_to_ArticleStreamComponentCO implements Converter<ArticleDO, ArticleStreamComponentCO> {

    final UserRepository userRepository;
    final ArticleRepository articleRepository;
    final ProfileDO_to_ProfileStreamComponentCO profileConverter;

    @Autowired
    public ArticleDO_to_ArticleStreamComponentCO(UserRepository userRepository, ArticleRepository articleRepository, ProfileDO_to_ProfileStreamComponentCO profileConverter) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.profileConverter = profileConverter;
    }

    @Synchronized@Nullable
    public List<ArticleStreamComponentCO> convert(Iterable<ArticleDO> sourceList){
        ArrayList<ArticleStreamComponentCO> articleComponentCOList = new ArrayList<>();
        for (ArticleDO articleDO : sourceList){
            articleComponentCOList.add(convert(articleDO));
        }
        return articleComponentCOList;
    }

    public List<ArticleStreamComponentCO> convert(List<ObjectId> source){
        return StreamSupport.stream(articleRepository.findAllById(source).spliterator(), false)
                .map(this::convert).collect(Collectors.toList());
    }

    @Synchronized
    @Nullable
    @Override
    public ArticleStreamComponentCO convert(ArticleDO source){

        final ArticleStreamComponentCO co = new ArticleStreamComponentCO();

        co.setName(source.getName());
        co.setDescription(source.getDescription());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setAuthorID(source.getUserID().toHexString());
        co.setAuthorName(userRepository.findBy_id(source.getUserID()).getUsername());
        userRepository.findById(source.getUserID()).ifPresent(user->co.setProfileStreamComponentCO(profileConverter.convert(user)));

        return co;
    }
}
