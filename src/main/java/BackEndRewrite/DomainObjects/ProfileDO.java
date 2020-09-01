package BackEndRewrite.DomainObjects;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

/**
 * DO for Users
 */
@Getter@Setter
@Document(collection="Profiles")
@NoArgsConstructor
public class ProfileDO {

    @org.springframework.data.annotation.Id
    String Id;

    String username;
    String password;

    List<String> articleIDList = new ArrayList<>();
    List<String> communityIDList = new ArrayList<>();
    List<String> discussionContentIDs = new ArrayList<>();

    public ProfileDO(String username,String password){
        this.username=username;
        this.password=password;
    }

}
