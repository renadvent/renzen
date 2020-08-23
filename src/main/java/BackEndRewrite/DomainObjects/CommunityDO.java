package BackEndRewrite.DomainObjects;

import BackEndRewrite.DomainObjectBaseClasses.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * DO for community
 */
@Getter@Setter
@Document(collection = "Communities")
public class CommunityDO extends BaseEntity {
    List<ProfileDO> profileDOList;
    List<ArticleDO> articleDOList;
}
