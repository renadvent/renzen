package com.ren.renzen.Converters;

import com.mongodb.lang.Nullable;
import com.ren.renzen.CommandObjects.DiscussionSectionCO;
import com.ren.renzen.DomainObjects.DiscussionSectionDO;
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

        co.setReplyCount(source.getReplies().size());
        DiscussionSectionDO_to_DiscussionSectionCO SectionConverter = new DiscussionSectionDO_to_DiscussionSectionCO();
        for (DiscussionSectionDO section : source.getReplies()) {
            co.getDiscussionSectionCOList().add(SectionConverter.convert(section));
        }
        return co;
    }
}
