package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.DomainObjects.CommunityDO;

import java.util.Optional;

public interface CommunityService {
    Optional<CommunityDO> findCommunityDOById(String id);
}
