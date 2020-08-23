package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileDO_to_ProfileStreamComponentCO implements Converter<ProfileDO,ProfileStreamComponentCO> {
    @Synchronized
    @Nullable
    @Override
    public ProfileStreamComponentCO convert(ProfileDO source){

        final ProfileStreamComponentCO co = new ProfileStreamComponentCO();

        return co;

    }
}
