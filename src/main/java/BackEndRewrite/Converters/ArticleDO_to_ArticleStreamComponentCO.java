package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.Repositories.UserRepository;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleDO_to_ArticleStreamComponentCO implements Converter<ArticleDO, ArticleStreamComponentCO> {

    final UserRepository repo;

    final ProfileDO_to_ProfileStreamComponentCO profileConverter;

    @Autowired
    public ArticleDO_to_ArticleStreamComponentCO(UserRepository repo, ProfileDO_to_ProfileStreamComponentCO profileConverter) {
        this.repo = repo;
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


    @Synchronized
    @Nullable
    @Override
    public ArticleStreamComponentCO convert(ArticleDO source){

        if (source==null){
            return null;
        }

        final ArticleStreamComponentCO co = new ArticleStreamComponentCO();

        co.setName(source.getName());
        co.setDescription(source.getDescription());
        co.setId(source.getId());
        co.setUserID(source.getUserID());

        //converts profile DO to CO
        //ProfileDO_to_ProfileStreamComponentCO converter = new ProfileDO_to_ProfileStreamComponentCO(discussionRepository);
        repo.findById(source.getUserID()).ifPresent(user->co.setUserIndexPageCO(profileConverter.convert(user)));

        return co;


    }
}
