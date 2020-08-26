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
    String name;
    String creatorID;
    List<ProfileDO> profileDOList;
    List<ArticleDO> articleDOList;
    String discussionID;

    /**
     * Used by /createCommunity to create a new Community
     */
    public CommunityDO(String name,String creatorID){
        this.name=name;
        this.creatorID=creatorID;
    }

}
