package com.ren.renzen.Validator;

import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.Payload.UserNamePassword;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserNamePasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProfileDO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        UserNamePassword userNamePassword = (UserNamePassword) object;

        // can check for password confirmation/validation etc...

        if (userNamePassword.getPassword().length() < 6) {
            errors.rejectValue("password", "length", "Password must be at least 6 characters");
        }

    }
}
