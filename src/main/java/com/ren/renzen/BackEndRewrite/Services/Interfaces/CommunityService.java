package com.ren.renzen.BackEndRewrite.Services.Interfaces;

import com.ren.renzen.BackEndRewrite.DomainObjects.CommunityDO;
import org.bson.types.ObjectId;

import java.util.List;

public interface CommunityService {
    CommunityDO findBy_id(ObjectId id);
    CommunityDO findDOByName(String name);
    Iterable<CommunityDO> findAll();
    boolean checkIfCommunityNameUsed(String name);
    List<CommunityDO> findBy_idIn(List<ObjectId> objectIdList);
    CommunityDO save(CommunityDO communityDO);
    List<CommunityDO> findByCreatorID(ObjectId objectId);
    List<ObjectId> findAllByCommunityIDAndTopic(ObjectId communityID, String Topic);
    List<CommunityDO> findAllPage();
}
