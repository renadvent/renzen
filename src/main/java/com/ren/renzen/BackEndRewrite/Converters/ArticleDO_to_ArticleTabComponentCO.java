package com.ren.renzen.BackEndRewrite.Converters;

import com.ren.renzen.BackEndRewrite.CommandObjects.TabComponentCOs.ArticleTabComponentCO;
import com.ren.renzen.BackEndRewrite.DomainObjects.ArticleDO;
import com.ren.renzen.BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import com.ren.renzen.BackEndRewrite.Repositories.ArticleRepository;
import com.ren.renzen.BackEndRewrite.Repositories.UserRepository;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.UserService;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts ArticleDO to ArticleComponentCO
 * which is a format that allows the React application to render the Article
 */

@Component
public class ArticleDO_to_ArticleTabComponentCO implements Converter<ArticleDO, ArticleTabComponentCO> {

    final UserRepository userRepo;
    final ArticleRepository articleRepo;

    final UserService userService;

    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

    final ArticleSectionDO_to_ArticleSectionCO articleSectionDO_to_articleSectionCO;

    @Autowired
    public ArticleDO_to_ArticleTabComponentCO(UserRepository repo, ArticleRepository articleRepo, UserService userService, ProfileDO_to_ProfileStreamComponentCO profileDOtoprofileStreamComponentCO, ArticleSectionDO_to_ArticleSectionCO articleSectionDO_to_articleSectionCO) {
        this.userRepo = repo;
        this.articleRepo = articleRepo;
        this.userService = userService;
        profileDO_to_profileStreamComponentCO = profileDOtoprofileStreamComponentCO;
        this.articleSectionDO_to_articleSectionCO = articleSectionDO_to_articleSectionCO;
    }

//    @Synchronized@Nullable
//    public List<ArticleComponentCO> convert(Iterable<ArticleDO> sourceList){
//        ArrayList<ArticleComponentCO> articleComponentCOList = new ArrayList<>();
//        for (ArticleDO articleDO : sourceList){
//            articleComponentCOList.add(convert(articleDO));
//        }
//        return articleComponentCOList;
//    }
//articleComponentCOList
@Synchronized
@Nullable
    public List<ArticleTabComponentCO> convert(List<ObjectId> articleDOIds){

        ArrayList<ArticleTabComponentCO> articleTabComponentCOList = new ArrayList<>();

        articleRepo.findAllById(articleDOIds).forEach(e->{
            articleTabComponentCOList.add(convert(e));
        });

        return articleTabComponentCOList;

    }

    @Synchronized@Nullable@Override
    public ArticleTabComponentCO convert(ArticleDO source){

        final ArticleTabComponentCO co = new ArticleTabComponentCO();

        co.setName(source.getName());
        co.setDescription(source.getDescription());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());

        co.setUserID(source.getUserID());
        //converts profile DO to CO
        //ProfileDO_to_ProfileStreamComponentCO ProfileConverter = new ProfileDO_to_ProfileStreamComponentCO(discussionRepository);
        userRepo.findById(source.getUserID()).ifPresent(user->co.setUser_streamComponentCO(profileDO_to_profileStreamComponentCO.convert(user)));
        //userService.findBy_id(source.get_id()).set

        //-------------------------

        co.setDiscussionID(source.getDiscussionID());

        //--------------------------

//        ArticleSectionDO_to_ArticleSectionCO ArticleSectionConverter = new ArticleSectionDO_to_ArticleSectionCO();

        System.out.println(source.getArticleSectionDOList());

        for (ArticleSectionDO articleSectionDO : source.getArticleSectionDOList()){

            //System.out.println(articleSectionDO.get_id());
            co.getArticleSectionCOList().add(articleSectionDO_to_articleSectionCO.convert(articleSectionDO));
        }

        //co.setArticleSectionCOList(ArticleSectionConverter.convert(source.getArticleSectionDOList()));

        return co;

    }
}
