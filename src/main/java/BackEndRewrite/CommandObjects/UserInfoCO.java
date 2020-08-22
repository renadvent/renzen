package BackEndRewrite.CommandObjects;

import BackEndRewrite.DomainObjectBases.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class UserInfoCO extends BaseEntity {
    String username;

    List<String> articleIDList;
    List<String> communityIDList;
    List<String> discussionContentIDList;
}
