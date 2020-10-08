package com.ren.renzen.Exceptions;

import lombok.Data;

@Data
public class OwnerMismatchResponse {
    String mismatch;

    OwnerMismatchResponse(String message) {
        this.mismatch = message;
    }
}
