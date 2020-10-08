package com.ren.renzen.ModelAssemblers.InterfaceAndAbstract;

import com.ren.renzen.CommandObjects.ArticleInfoComponentCO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

/**
 *allows transforming of lists of domain objects
 * to DTO's
 * with to hateos response
 * and wraps
 */

public abstract class DOMAIN_VIEW_ASSEMBLER_SUPPORT<DOMAIN,CO extends RepresentationModel<CO>>
//    extends CollectionModel<CO>
        implements DOMAIN_VIEW_ASSEMBLER<DOMAIN, CO>  {

    public CollectionModel<CO> assembleDomainToPublicModelViewCollection(List<DOMAIN> entities){
        return new CollectionModel<>(entities.stream().map(this::assembleDomainToPublicModelView).collect(Collectors.toList()));
    }
    public CollectionModel<CO> assembleDomainToFullModelViewCollection(List<DOMAIN> entities){
        return new CollectionModel<>(entities.stream().map(this::assembleDomainToFullModelView).collect(Collectors.toList()));
    }

    public Authentication getAuth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public abstract CO addLinksWithCurrentAuthentication(CO entity);
}
