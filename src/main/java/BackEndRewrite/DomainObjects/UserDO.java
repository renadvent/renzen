package BackEndRewrite.DomainObjects;

import com.ren.renzen.DomainObjects.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection="Users")
public class UserDO extends BaseEntity {
    String username;
    String password;

    List<String> articleIDList;
    List<String> communityIDList;
    List<String> discussionContentIDs;
}