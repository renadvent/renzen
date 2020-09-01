package BackEndRewrite.DomainObjects;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
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
    ObjectId Id;

    String username;
    String password;

    List<ObjectId> articleIDList = new ArrayList<>();
    List<ObjectId> communityIDList = new ArrayList<>();
    List<ObjectId> discussionContentIDs = new ArrayList<>();

    public ProfileDO(String username,String password){
        this.username=username;
        this.password=password;
    }

}
