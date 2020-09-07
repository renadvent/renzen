package com.ren.renzen.BackEndRewrite.Converters;

import com.ren.renzen.BackEndRewrite.CommandObjects.SubCommunityComponentCOs.DiscussionComponentCO;
import com.ren.renzen.BackEndRewrite.DomainObjects.DiscussionDO;
import com.ren.renzen.BackEndRewrite.DomainObjects.Subsections.DiscussionSectionDO;
import com.ren.renzen.BackEndRewrite.Repositories.DiscussionRepository;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DiscussionDO_to_DiscussionComponentCO implements Converter<DiscussionDO, DiscussionComponentCO> {

    final DiscussionRepository discussionRepository;

    public DiscussionDO_to_DiscussionComponentCO(DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
    }

    /**
     * converts discussionID to discussionComponentCO
     * @param discussionID
     * @return
     */
    @Synchronized
    @Nullable
    public DiscussionComponentCO convert(ObjectId discussionID){
        return discussionRepository.findById(discussionID)
                .map(discussionDO -> convert(discussionDO))
                .orElse(null);
    }

    @Synchronized
    @Nullable
    @Override
    public DiscussionComponentCO convert(DiscussionDO source) {
        DiscussionComponentCO co = new DiscussionComponentCO();

        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());

        //-------------------


        DiscussionSectionDO_to_DiscussionSectionCO SectionConverter = new DiscussionSectionDO_to_DiscussionSectionCO();


        for (DiscussionSectionDO section : source.getDiscussionSectionDOList())
            co.getDiscussionSectionCOList().add(SectionConverter.convert(section));
        return co;
    }
}