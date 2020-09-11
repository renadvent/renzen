package com.ren.renzen.BackEndRewrite.Services;

import com.ren.renzen.BackEndRewrite.DomainObjects.CommunityDO;
import com.ren.renzen.BackEndRewrite.Repositories.CommunityRepository;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.CommunityService;
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
    public CommunityDO findBy_id(ObjectId id) {

        Optional<CommunityDO> communityDOOptional = communityRepository.findById(id);

        if (communityDOOptional.isPresent()){
            return communityDOOptional.get();
        }else{
            throw new ResourceNotFoundException("Community not found");
        }

    }

    @Override
    public CommunityDO findDOByName(String name) {

        Optional<CommunityDO> communityDOOptional = communityRepository.findByName(name);

        if (communityDOOptional.isPresent()){
            return communityDOOptional.get();
        }else{
            throw new ResourceNotFoundException("Community not found");
        }
    }

    @Override
    public Iterable<CommunityDO> findAll() {

        return communityRepository.findAll();
    }

    @Override
    public boolean checkIfCommunityNameUsed(String name){
        Optional<CommunityDO> communityDOOptional = communityRepository.findByName(name);

        return communityDOOptional.isPresent();
    }

    @Override
    public List<CommunityDO> findBy_idIn(List<ObjectId> objectIdList) {
        return communityRepository.findBy_idIn(objectIdList);
    }

    @Override
    public CommunityDO save(CommunityDO communityDO) {
        return communityRepository.save(communityDO);
    }

    @Override
    public List<CommunityDO> findByCreatorID(ObjectId objectId) {
        return communityRepository.findByCreatorID(objectId);
    }

    //TODO this
    @Override
    public List<ObjectId> findAllByCommunityIDAndTopic(ObjectId communityID, String Topic) {
        return null;
    }

    @Override
    public List<CommunityDO> findAllPage() {
        var paging = PageRequest.of(0,10, Sort.by("_id"));
        return communityRepository.findAll(paging).getContent();
    }


}
