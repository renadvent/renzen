package BackEndRewrite.Services;

import BackEndRewrite.DomainObjects.CommunityDO;
import BackEndRewrite.Services.Interfaces.CommunityService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Override
    public Optional<CommunityDO> findCommunityDOById(String id) {
        return Optional.empty();
    }
}
