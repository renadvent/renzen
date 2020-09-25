package com.ren.renzen.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileNotFoundResponse {
    private String profileNotFound;

    public ProfileNotFoundResponse(String profileNotFound) {
        this.profileNotFound = profileNotFound;
    }
}
