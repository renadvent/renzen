package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.DomainObjects.CommunityDO;

import java.util.Optional;

public interface CommunityService {
    CommunityDO findCommunityDOById(String id);
    CommunityDO findDOByName(String name);
    CommunityDO save(CommunityDO communityDO);
}
