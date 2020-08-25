package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.ContentCOs.ArticleSectionCO;
import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import BackEndRewrite.Repositories.ArticleRepository;
import BackEndRewrite.Repositories.UserRepository;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Converts ArticleDO to ArticleComponentCO
 * which is a format that allows the React application to render the Article
 */

@Component
public class ArticleDO_to_ArticleComponentCO implements Converter<ArticleDO,ArticleComponentCO> {

    final UserRepository userRepo;
    final ArticleRepository articleRepo;

    @Autowired
    public ArticleDO_to_ArticleComponentCO(UserRepository repo, ArticleRepository articleRepo) {
        this.userRepo = repo;
        this.articleRepo = articleRepo;
    }

    @Synchronized@Nullable@Override
    public ArticleComponentCO convert(ArticleDO source){

        final ArticleComponentCO co = new ArticleComponentCO();

        co.setName(source.getName());
        co.setDescription(source.getDescription());
        co.setId(source.getId());

        co.setUserID(source.getUserID());
        //converts profile DO to CO
        ProfileDO_to_ProfileStreamComponentCO ProfileConverter = new ProfileDO_to_ProfileStreamComponentCO();
        userRepo.findById(source.getUserID()).ifPresent(user->co.setUser_streamComponentCO(ProfileConverter.convert(user)));

        //-------------------------

        co.setDiscussionID(source.getDiscussionID());

        //--------------------------

        ArticleSectionDO_to_ArticleSectionCO ArticleSectionConverter = new ArticleSectionDO_to_ArticleSectionCO();

        for (ArticleSectionDO sectionDO : source.getArticleSectionDOList()){
            co.getArticleSectionCOList().add(ArticleSectionConverter.convert(sectionDO));
        }

        //co.setArticleSectionCOList(ArticleSectionConverter.convert(source.getArticleSectionDOList()));

        return co;

    }
}
