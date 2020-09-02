package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.DomainObjects.CommunityDO;
import org.bson.types.ObjectId;

import java.util.List;

public interface CommunityService {
    CommunityDO findBy_id(ObjectId id);
    CommunityDO findDOByName(String name);
    Iterable<CommunityDO> findAll();

    boolean checkIfCommunityNameUsed(String name);

    CommunityDO save(CommunityDO communityDO);

    List<CommunityDO> findByCreatorID(ObjectId objectId);
}
