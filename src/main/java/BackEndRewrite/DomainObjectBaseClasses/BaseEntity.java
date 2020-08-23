package BackEndRewrite.DomainObjectBaseClasses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * Used for ArticleDO, CommunityDO, DiscussionDO, and UserDO
 */
@Getter@Setter
public class BaseEntity {
    @Id
    String id;
}
