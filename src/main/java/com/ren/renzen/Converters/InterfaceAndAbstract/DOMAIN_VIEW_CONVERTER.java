package com.ren.renzen.Converters.InterfaceAndAbstract;

/**
 * When a controller is accessed with an authorization header (login)
 * this interface can be used to convert to public if login does not grant access
 * or private if login does grant access
 */
public interface DOMAIN_VIEW_CONVERTER<DOMAIN, CO> {

    public static final String ACCESS_TYPE_PUBLIC = "ACCESS_TYPE_PUBLIC";
    public static final String ACCESS_TYPE_FULL = "ACCESS_TYPE_FULL";

    CO convertDomainToPublicView(DOMAIN domain);
    CO convertDomainToFullView(DOMAIN domain);
}
