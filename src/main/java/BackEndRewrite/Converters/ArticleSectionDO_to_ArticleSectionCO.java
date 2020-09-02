package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.ContentCOs.ArticleSectionCO;
import BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArticleSectionDO_to_ArticleSectionCO implements Converter<ArticleSectionDO, ArticleSectionCO> {
    @Synchronized
    @Nullable
    @Override
    public ArticleSectionCO convert(ArticleSectionDO source) {
        ArticleSectionCO co = new ArticleSectionCO();
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setHeading(source.getHeading());
        co.setContent(source.getContent());
        co.setAuthorID(source.getAuthor());
        co.setUpvotes(source.getUpvotes());
        co.setDownvotes(source.getDownvotes());
        return co;
    }
}
