package BackEndRewrite.DomainObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

/**
 * DO for community
 */
@Getter@Setter
@Document(collection = "Communities")
@NoArgsConstructor
public class CommunityDO{

    @org.springframework.data.annotation.Id
    String Id;
    String name;
    String creatorID;
    List<String> profileDOList= new ArrayList<>();
    List<String> articleDOList= new ArrayList<>();
    String discussionID;

    /**
     * Used by /createCommunity to create a new Community
     */
    public CommunityDO(String name,String creatorID){
        this.name=name;
        this.creatorID=creatorID;
    }

}
