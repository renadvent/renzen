package BackEndRewrite.Controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter@NoArgsConstructor
public class SitePayloads {
    static class UserNamePassword{
        String username;
        String password;

        public UserNamePassword(String username,String password){
            this.username=username;
            this.password=password;
        }
    }
}
