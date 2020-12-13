package com.ren.renzen.ResourceObjects.Payload;

import com.ren.renzen.ResourceObjects.DomainObjects.ArticleDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class UpdateArticlePayload {

//    @NotBlank(message = "community must not be blank")
    String articleName;

//    @NotNull(message = "community must not be null")
//            @NotBlank(message = "community must not be blank")
    ObjectId communityID;
    List<ArticleDO.ArticleSectionDO> articleSectionDOList;

    String workName;
    String tags;

    List<ArticleDO.PollOption> pollOptions;
}
