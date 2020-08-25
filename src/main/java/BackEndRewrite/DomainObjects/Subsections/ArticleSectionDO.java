package BackEndRewrite.DomainObjects.Subsections;

import BackEndRewrite.DomainObjectBaseClasses.BaseContent;
import lombok.Getter;
import lombok.Setter;

/**
 * contains the content for a section of an article
 */
@Getter@Setter
public class ArticleSectionDO extends BaseContent {
    String heading;
}
