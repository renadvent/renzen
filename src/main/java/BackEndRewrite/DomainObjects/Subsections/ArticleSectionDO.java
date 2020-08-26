package BackEndRewrite.DomainObjects.Subsections;

import BackEndRewrite.DomainObjects.DomainObjectBaseClasses.BaseSection;
import lombok.Getter;
import lombok.Setter;

/**
 * contains the content for a section of an article
 */
@Getter@Setter
public class ArticleSectionDO extends BaseSection {
    String heading;
}
