package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.Repositories.UserRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ArticleDO_to_ArticleStreamComponentCO implements Converter<ArticleDO, ArticleStreamComponentCO> {

    final UserRepository repo;

    public ArticleDO_to_ArticleStreamComponentCO(UserRepository repo) {
        this.repo = repo;
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
        ProfileDO_to_ProfileStreamComponentCO converter = new ProfileDO_to_ProfileStreamComponentCO();
        repo.findById(source.getUserID()).ifPresent(user->co.setUserIndexPageCO(converter.convert(user)));

        return co;


    }
}
