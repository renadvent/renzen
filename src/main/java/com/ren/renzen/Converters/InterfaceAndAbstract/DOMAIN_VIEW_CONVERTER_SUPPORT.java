package com.ren.renzen.Converters.InterfaceAndAbstract;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DOMAIN_VIEW_CONVERTER_SUPPORT<DOMAIN, CO>
        implements DOMAIN_VIEW_CONVERTER<DOMAIN, CO> {

    public List<CO> convertListToPublicView(List<DOMAIN> domainList) {
        return domainList.stream().map(this::convertDomainToPublicView).collect(Collectors.toList());
    }

    public List<CO> convertListToFullView(List<DOMAIN> domainList) {
        return domainList.stream().map(this::convertDomainToFullView).collect(Collectors.toList());
    }


}
