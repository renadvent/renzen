package BackEndRewrite.Controllers;

public class SitePayloads {
    static class UserNamePassword{
        String username;
        String password;

        //public UserNamePassword(){};
        public UserNamePassword(String username,String password){
            this.username=username;
            this.password=password;
        }
    }
}
