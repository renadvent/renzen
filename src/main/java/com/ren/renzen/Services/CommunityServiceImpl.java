package com.ren.renzen.Services;

import com.ren.renzen.Repositories.CommunityRepository;
import com.ren.renzen.ResourceObjects.DomainObjects.CommunityDO;
import com.ren.renzen.Services.Interfaces.CommunityService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityServiceImpl implements CommunityService {

    final CommunityRepository communityRepository;

    public CommunityServiceImpl(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    public CommunityDO save(CommunityDO communityDO) {
        return communityRepository.save(communityDO);
    }

    @Override
    public CommunityDO findBy_id(ObjectId id) {

        Optional<CommunityDO> communityDOOptional = communityRepository.findById(id);

        if (communityDOOptional.isPresent()) {
            return communityDOOptional.get();
        } else {
            throw new ResourceNotFoundException("Community not found");
        }
    }

    @Override
    public List<CommunityDO> findAll(String username) {

        return communityRepository.findAllByCreatorName(username);
    }

    @Override
    public boolean checkIfCommunityNameUsed(String name) {
        return communityRepository.findByName(name).isPresent();
    }

    @Override
    public List<CommunityDO> findBy_idIn(List<ObjectId> objectIdList) {
        return communityRepository.findBy_idIn(objectIdList);
    }

    @Override
    public CommunityDO saveOrUpdateCommunity(CommunityDO communityDO, String principalName) {
        return communityRepository.save(communityDO);
    }

    //TODO this
    @Override
    public List<ObjectId> findAllByCommunityIDAndTopic(ObjectId communityID, String Topic) {
        return null;
    }

    @Override
    public List<CommunityDO> findAllPage() {
        var paging = PageRequest.of(0, 10, Sort.by("_id").descending());
        return communityRepository.findAll(paging).getContent();
    }
}
