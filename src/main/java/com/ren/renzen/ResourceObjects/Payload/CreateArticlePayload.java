package com.ren.renzen.ResourceObjects.Payload;

import com.ren.renzen.ResourceObjects.DomainObjects.ArticleDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateArticlePayload {

    @NotBlank(message = "Article Must Have a Name")
    String articleName;
    String topic;
    String description;

    String workName;

    ObjectId CommunityID;
    List<ArticleDO.ArticleSectionDO> articleSectionDOList;


}
