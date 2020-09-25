package com.ren.renzen.Services.Interfaces;

import com.ren.renzen.DomainObjects.CommunityDO;
import org.bson.types.ObjectId;

import java.util.List;

public interface CommunityService {
    CommunityDO findBy_id(ObjectId id);

    List<CommunityDO> findAll();

    boolean checkIfCommunityNameUsed(String name);

    List<CommunityDO> findBy_idIn(List<ObjectId> objectIdList);

    CommunityDO save(CommunityDO communityDO);

    List<ObjectId> findAllByCommunityIDAndTopic(ObjectId communityID, String Topic);

    List<CommunityDO> findAllPage();
}
