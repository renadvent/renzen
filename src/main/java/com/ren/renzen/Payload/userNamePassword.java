package com.ren.renzen.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
    @Setter
    @NoArgsConstructor
        public class userNamePassword {

    @NotBlank(message = "username must not be blank")
            String username;

    @NotBlank(message = "password must not be blank")
            String password;

            String confirmPassword; //?

            public userNamePassword(String username, String password) {
                this.username = username;
                this.password = password;
            }
        }


