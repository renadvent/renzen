package com.ren.renzen.BackEndRewrite.Converters;

import com.ren.renzen.BackEndRewrite.CommandObjects.DiscussionSectionCO;
import com.ren.renzen.BackEndRewrite.DomainObjects.DiscussionSectionDO;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DiscussionSectionDO_to_DiscussionSectionCO implements Converter<DiscussionSectionDO, DiscussionSectionCO> {
    @Synchronized
    @Nullable
    @Override
    public DiscussionSectionCO convert(DiscussionSectionDO source) {
        DiscussionSectionCO co = new DiscussionSectionCO();
//
//        co.setAuthorID(source.getAuthor());
//        co.setContent(source.getContent());
//        co.set_id(source.get_id().toHexString());
//        co.setObjectId(source.get_id());

        co.setReplyCount(source.getReplies().size());


//------------------------

        DiscussionSectionDO_to_DiscussionSectionCO SectionConverter = new DiscussionSectionDO_to_DiscussionSectionCO();

        for (DiscussionSectionDO section : source.getReplies()){
            co.getDiscussionSectionCOList().add(SectionConverter.convert(section));
        }
        return co;

    }
}
