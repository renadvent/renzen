package com.ren.renzen.Services;

import com.ren.renzen.ResourceObjects.DomainObjects.ProfileDO;
import com.ren.renzen.Exceptions.ProfileNotFoundException;
import com.ren.renzen.Repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var profileDO = userRepository.findByUsername(username);

        if (profileDO.isEmpty()) {
            throw new ProfileNotFoundException("User not found");
        } else {
            return profileDO.get();
        }

    }

    @Transactional
    public ProfileDO loadUserById(ObjectId id) {

        var profileDO = userRepository.findBy_id(id);

        if (profileDO.isEmpty()) {
            throw new ProfileNotFoundException("User not found");
        } else {
            return profileDO.get();
        }

    }
}
