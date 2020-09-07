package BackEndRewrite.ModelAssemblers;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.Controllers.IndexController;
import BackEndRewrite.Converters.ArticleDO_to_ArticleStreamComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArticleStreamCOAssembler extends RepresentationModelAssemblerSupport<ArticleDO, ArticleStreamComponentCO> {

    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;

    public ArticleStreamCOAssembler(ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO) {
        super(IndexController.class, ArticleStreamComponentCO.class);
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
    }

    @Override
    public ArticleStreamComponentCO toModel(ArticleDO entity) {

        ArticleStreamComponentCO articleStreamComponentCO = articleDO_to_articleStreamComponentCO.convert(entity);

        return articleStreamComponentCO
                .add(List.of(
                        linkTo(methodOn(IndexController.class).getArticleStreamComponentCO(articleStreamComponentCO.getObjectId())).withRel("Stream_Version"),
                        linkTo(methodOn(IndexController.class).getArticleTabComponentCO(articleStreamComponentCO.getObjectId())).withRel("Tab_Version")));
    }
}
