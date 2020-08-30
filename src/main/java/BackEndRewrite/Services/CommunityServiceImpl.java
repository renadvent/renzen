package BackEndRewrite.Services;

import BackEndRewrite.DomainObjects.CommunityDO;
import BackEndRewrite.Repositories.CommunityRepository;
import BackEndRewrite.Services.Interfaces.CommunityService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommunityServiceImpl implements CommunityService {

    final CommunityRepository communityRepository;

    public CommunityServiceImpl(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    public CommunityDO findCommunityDOById(String id) {

        Optional<CommunityDO> communityDOOptional = communityRepository.findById(id);

        if (communityDOOptional.isPresent()){
            return communityDOOptional.get();
        }else{
            throw new RuntimeException("Community not found");
        }

    }

    @Override
    public CommunityDO findDOByName(String name) {

        Optional<CommunityDO> communityDOOptional = communityRepository.findByName(name);

        if (communityDOOptional.isPresent()){
            return communityDOOptional.get();
        }else{
            throw new RuntimeException("Community not found");
        }
    }

    @Override
    public CommunityDO save(CommunityDO communityDO) {
        return communityRepository.save(communityDO);
    }


}
