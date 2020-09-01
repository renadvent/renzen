package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.ContentCOs.DiscussionSectionCO;
import BackEndRewrite.DomainObjects.Subsections.DiscussionSectionDO;
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

        co.setAuthor(source.getAuthor());
        co.setContent(source.getContent());
        co.setId(source.get_id());

        co.setReplyCount(source.getReplies().size());


//------------------------

        DiscussionSectionDO_to_DiscussionSectionCO SectionConverter = new DiscussionSectionDO_to_DiscussionSectionCO();

        for (DiscussionSectionDO section : source.getReplies()){
            co.getDiscussionSectionCOList().add(SectionConverter.convert(section));
        }
        return co;

    }
}
