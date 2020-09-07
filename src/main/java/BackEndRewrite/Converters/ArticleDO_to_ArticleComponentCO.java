package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import BackEndRewrite.Repositories.ArticleRepository;
import BackEndRewrite.Repositories.UserRepository;
import BackEndRewrite.Services.Interfaces.UserService;
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
public class ArticleDO_to_ArticleComponentCO implements Converter<ArticleDO,ArticleComponentCO> {

    final UserRepository userRepo;
    final ArticleRepository articleRepo;

    final UserService userService;

    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

    final ArticleSectionDO_to_ArticleSectionCO articleSectionDO_to_articleSectionCO;

    @Autowired
    public ArticleDO_to_ArticleComponentCO(UserRepository repo, ArticleRepository articleRepo, UserService userService, ProfileDO_to_ProfileStreamComponentCO profileDOtoprofileStreamComponentCO, ArticleSectionDO_to_ArticleSectionCO articleSectionDO_to_articleSectionCO) {
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
    public List<ArticleComponentCO> convert(List<ObjectId> articleDOIds){

        ArrayList<ArticleComponentCO> articleComponentCOList = new ArrayList<>();

        articleRepo.findAllById(articleDOIds).forEach(e->{
            articleComponentCOList.add(convert(e));
        });

        return articleComponentCOList;

    }

    @Synchronized@Nullable@Override
    public ArticleComponentCO convert(ArticleDO source){

        final ArticleComponentCO co = new ArticleComponentCO();

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
