package BackEndRewrite.DomainObjects.Subsections;

import BackEndRewrite.DomainObjects.DomainObjectBaseClasses.BaseSection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * contains the content for a section of an article
 */
@Getter@Setter
@NoArgsConstructor
public class ArticleSectionDO extends BaseSection {
    String heading;

    ArticleSectionDO(String heading, String content){
        this.heading=heading;
        this.setContent(content);
    }
}
