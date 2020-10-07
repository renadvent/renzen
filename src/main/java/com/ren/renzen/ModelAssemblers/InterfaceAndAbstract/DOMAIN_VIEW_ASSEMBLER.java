package com.ren.renzen.ModelAssemblers.InterfaceAndAbstract;

public interface DOMAIN_VIEW_ASSEMBLER<DOMAIN, CO> {
    CO assembleDomainToPublicModelView(DOMAIN domain);
    CO assembleDomainToFullModelView(DOMAIN domain);
}
