package BackEndRewrite.DomainObjects;

import BackEndRewrite.DomainObjects.DomainObjectBaseClasses.BaseDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * DO for community
 */
@Getter@Setter
@Document(collection = "Communities")
@NoArgsConstructor
public class CommunityDO extends BaseDomain {
    String name;
    String creatorID;
    List<String> profileDOList;
    List<String> articleDOList;
    String discussionID;

    /**
     * Used by /createCommunity to create a new Community
     */
    public CommunityDO(String name,String creatorID){
        this.name=name;
        this.creatorID=creatorID;
    }

}
