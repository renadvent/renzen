package com.ren.renzen.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

    @Getter
    @Setter
    @NoArgsConstructor
        public class userNamePassword {
            String username;
            String password;

            public userNamePassword(String username, String password) {
                this.username = username;
                this.password = password;
            }
        }


