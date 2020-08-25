package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.DiscussionComponentCO;
import BackEndRewrite.DomainObjects.DiscussionDO;
import BackEndRewrite.DomainObjects.Subsections.DiscussionSectionDO;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DiscussionDO_to_DiscussionComponentCO implements Converter<DiscussionDO, DiscussionComponentCO> {
    @Synchronized
    @Nullable
    @Override
    public DiscussionComponentCO convert(DiscussionDO source) {
        DiscussionComponentCO co = new DiscussionComponentCO();

        //-------------------


        DiscussionSectionDO_to_DiscussionSectionCO SectionConverter = new DiscussionSectionDO_to_DiscussionSectionCO();


        for (DiscussionSectionDO section : source.getDiscussionSectionDOList())
            co.getDiscussionSectionCOList().add(SectionConverter.convert(section));
        return co;
    }
}
