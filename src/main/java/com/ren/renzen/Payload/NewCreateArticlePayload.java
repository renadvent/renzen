package com.ren.renzen.Payload;

import com.ren.renzen.DomainObjects.ArticleSectionDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
public class NewCreateArticlePayload {

    String articleName;
    String topic;
    String description;

    ObjectId CommunityID;
    List<ArticleSectionDO> articleSectionDOList;

    //NEW

    String workName;
    String postType;
    String tags;
    String image;
    String postText;
    String pollOptions;
    //List<String> pollOptions = new ArrayList<>();
}
