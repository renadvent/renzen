package com.ren.renzen.ResourceObjects.Payload;

import com.ren.renzen.ResourceObjects.DomainObjects.ArticleDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
public class UpdateArticlePayload {

    String articleName;

    ObjectId CommunityID;
    List<ArticleDO.ArticleSectionDO> articleSectionDOList;

    String workName;
    String tags;

    String pollOptions;
}
