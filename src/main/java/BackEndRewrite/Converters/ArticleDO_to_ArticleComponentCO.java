package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.Repositories.ArticleRepository;
import BackEndRewrite.Repositories.UserRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Converts ArticleDO to ArticleComponentCO
 * which is a format that allows the React application to render the Article
 */

@Component
public class ArticleDO_to_ArticleComponentCO implements Converter<ArticleDO,ArticleComponentCO> {

    final UserRepository userRepo;
    final ArticleRepository articleRepo;

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
        ProfileDO_to_ProfileStreamComponentCO converter = new ProfileDO_to_ProfileStreamComponentCO();
        userRepo.findById(source.getUserID()).ifPresent(user->co.setUser_streamComponentCO(converter.convert(user)));

        //-------------------------

        co.setDiscussionID(source.getDiscussionID());

        //-------------------------

        //working HERE----------------
        //get COs from IDs

        co.setArticleContentIDList(source.getArticleSectionDOIDList());

        for (String contentID : co.getArticleContentIDList()){





        }





        return co;


    }
}
