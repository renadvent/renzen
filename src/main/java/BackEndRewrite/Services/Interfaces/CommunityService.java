package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.DomainObjects.CommunityDO;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface CommunityService {
    CommunityDO findCommunityDOById(ObjectId id);
    CommunityDO findDOByName(String name);

    boolean checkIfCommunityNameUsed(String name);

    CommunityDO save(CommunityDO communityDO);
}
