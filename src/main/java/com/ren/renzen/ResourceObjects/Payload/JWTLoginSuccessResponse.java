package com.ren.renzen.ResourceObjects.Payload;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class JWTLoginSuccessResponse {

    ObjectId id;
    private boolean success;
    private String token;

    public JWTLoginSuccessResponse(boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    public JWTLoginSuccessResponse(boolean success, String token, ObjectId id) {
        this.success = success;
        this.token = token;
        this.id = id;
    }

    @Override
    public String toString() {
        return "JWTLoginSuccessResponse{" +
                "success=" + success +
                ", token='" + token + '\'' +
                '}';
    }
}
