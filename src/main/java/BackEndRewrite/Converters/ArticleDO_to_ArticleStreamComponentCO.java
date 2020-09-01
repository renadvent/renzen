package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.Repositories.ArticleRepository;
import BackEndRewrite.Repositories.UserRepository;
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

    final UserRepository repo;
    final ArticleRepository articleRepository;

    final ProfileDO_to_ProfileStreamComponentCO profileConverter;

    @Autowired
    public ArticleDO_to_ArticleStreamComponentCO(UserRepository repo, ArticleRepository articleRepository, ProfileDO_to_ProfileStreamComponentCO profileConverter) {
        this.repo = repo;
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

        if (source==null){
            return null;
        }

        final ArticleStreamComponentCO co = new ArticleStreamComponentCO();

        co.setName(source.getName());
        co.setDescription(source.getDescription());
        co.set_id(source.get_id().toHexString());
        co.setUserID(source.getUserID());

        //converts profile DO to CO
        //ProfileDO_to_ProfileStreamComponentCO converter = new ProfileDO_to_ProfileStreamComponentCO(discussionRepository);
        repo.findById(source.getUserID()).ifPresent(user->co.setUserIndexPageCO(profileConverter.convert(user)));

        return co;


    }
}
