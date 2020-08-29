package BackEndRewrite.DomainObjects;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * DO for Users
 */
@Getter@Setter
@Document(collection="Users")
public class ProfileDO {
    String username;
    String password;

    List<String> articleIDList;
    List<String> communityIDList;
    List<String> discussionContentIDs;

    public ProfileDO(String username,String password){
        this.username=username;
        this.password=password;
    }

}
