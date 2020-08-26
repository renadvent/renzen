package BackEndRewrite;

import com.ren.renzen.DomainObjects.*;
import com.ren.renzen.DomainObjects.BaseContent;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class config implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Community.class, ArticleDomainObject.class, BaseContent.class,
                UserDomainObject.class);
    }
}