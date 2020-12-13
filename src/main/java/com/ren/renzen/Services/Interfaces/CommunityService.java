package com.ren.renzen.Services.Interfaces;

import com.ren.renzen.ResourceObjects.DomainObjects.CommunityDO;
import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.List;

public interface CommunityService {
    CommunityDO save(CommunityDO communityDO);

    CommunityDO findBy_id(ObjectId id);

    List<CommunityDO> findAll(String username);

    boolean checkIfCommunityNameUsed(String name);

    List<CommunityDO> findBy_idIn(Collection<ObjectId> objectIdList);

//    CommunityDO save(CommunityDO communityDO);

    CommunityDO saveOrUpdateCommunity(CommunityDO communityDO, String principalName);

    List<ObjectId> findAllByCommunityIDAndTopic(ObjectId communityID, String Topic);

    List<CommunityDO> findAllPage();
}
