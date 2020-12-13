package com.ren.renzen.Controllers;

import com.ren.renzen.Controllers.Security.JwtTokenProvider;
import com.ren.renzen.Converters.ArticleConverter;
import com.ren.renzen.Converters.CommunityConverter;
import com.ren.renzen.Converters.ProfileConverter;
import com.ren.renzen.ModelAssemblers.ArticleAssembler;
import com.ren.renzen.ModelAssemblers.CommunityAssembler;
import com.ren.renzen.ModelAssemblers.ProfileAssembler;
import com.ren.renzen.ResourceObjects.CommandObjects.ProfileDTOs;
import com.ren.renzen.ResourceObjects.DomainObjects.CommunityDO;
import com.ren.renzen.ResourceObjects.DomainObjects.ProfileDO;
import com.ren.renzen.ResourceObjects.Payload.JWTLoginSuccessResponse;
import com.ren.renzen.ResourceObjects.Payload.LoginRequestPayload;
import com.ren.renzen.ResourceObjects.Payload.RegisterPayload;
import com.ren.renzen.ResourceObjects.Payload.addBookmarkPayload;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import com.ren.renzen.Services.MapValidationErrorService;
import com.ren.renzen.Validator.UserNamePasswordValidator;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

import static com.ren.renzen.Controllers.Constants.CONTROLLER_PATHS.User.*;
import static com.ren.renzen.additional.KEYS.TOKEN_PREFIX;

public class UserController {
    @RestController
    public static class UserEditorController {

        //services
        final UserService userService;
        final ArticleService articleService;
        final CommunityService communityService;

        //converters
        final ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;
        final ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
        final ProfileConverter.ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;
        final ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
        final CommunityConverter.CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;
        final CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

        //assemblers
        final ArticleAssembler.ArticleTabCOAssembler articleTabCOAssembler;
        final ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler;
        final ProfileAssembler.ProfileTabCOAssembler profileTabCOAssembler;
        final CommunityAssembler.CommunityTabCOAssembler communityTabCOAssembler;
        final CommunityAssembler.CommunityStreamCOAssembler communityStreamCOAssembler;
        final ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler;

        //Error service
        final MapValidationErrorService mapValidationErrorService;

        //Validation Service
        final UserNamePasswordValidator userNamePasswordValidator;

        //Token Provider
        final JwtTokenProvider jwtTokenProvider;

        //Authentication Manager
        final AuthenticationManager authenticationManager;

        public UserEditorController(UserService userService, ArticleService articleService, CommunityService communityService, ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileConverter.ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityConverter.CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleAssembler.ArticleTabCOAssembler articleTabCOAssembler, ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler, ProfileAssembler.ProfileTabCOAssembler profileTabCOAssembler, CommunityAssembler.CommunityTabCOAssembler communityTabCOAssembler, CommunityAssembler.CommunityStreamCOAssembler communityStreamCOAssembler, ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler, MapValidationErrorService mapValidationErrorService, UserNamePasswordValidator userNamePasswordValidator, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
            this.userService = userService;
            this.articleService = articleService;
            this.communityService = communityService;
            this.articleDO_to_articleTabComponentCO = articleDO_to_articleTabComponentCO;
            this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
            this.profileDO_to_profileTabComponentCO = profileDO_to_profileTabComponentCO;
            this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
            this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
            this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
            this.articleTabCOAssembler = articleTabCOAssembler;
            this.profileStreamCOAssembler = profileStreamCOAssembler;
            this.profileTabCOAssembler = profileTabCOAssembler;
            this.communityTabCOAssembler = communityTabCOAssembler;
            this.communityStreamCOAssembler = communityStreamCOAssembler;
            this.articleStreamCOAssembler = articleStreamCOAssembler;
            this.mapValidationErrorService = mapValidationErrorService;
            this.userNamePasswordValidator = userNamePasswordValidator;
            this.jwtTokenProvider = jwtTokenProvider;
            this.authenticationManager = authenticationManager;
        }

        //TODO work on
        @RequestMapping(DELETE_PROFILE)
        public ResponseEntity<?> deleteProfile(@PathVariable ObjectId id, Principal principal){

            var profile = userService.findBy_id(id);
            var profileName = profile.getUsername();

            if (profileName.equals(principal.getName())){

                //TODO delete profile

                //delete articles

                //remove from communities

                return ResponseEntity.ok().build();


            }else{
                return ResponseEntity.badRequest().build();
            }

        }

        @PostMapping(ADD_BOOKMARK)
        public ResponseEntity<?> addBookmark(@RequestBody @Valid addBookmarkPayload payload, Principal principal) {

            //var profileDO = userService.findBy_id(payload.getUserId());
            var profileDO = userService.findByUsername(principal.getName());

            var articleDO = articleService.findBy_id(payload.getArticleId());

            profileDO.getArticleBookmarkIDList().add(articleDO.get_id());
            userService.update(profileDO);

            return ResponseEntity.ok(null);
        }

        @PostMapping(REGISTER)
        public ResponseEntity<?> Register(@Valid @RequestBody RegisterPayload registerPayload, BindingResult result) {

            userNamePasswordValidator.validate(registerPayload, result);

            ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
            if (errorMap != null) return errorMap;

            if (!registerPayload.getPassword().equals(registerPayload.getConfirmPassword())
                    ||
                    userService.findByEmail(registerPayload.getEmail()).isPresent()
            ) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            var profileDO = new ProfileDO();
            profileDO.setUsername(registerPayload.getUsername());
            profileDO.setPassword(registerPayload.getPassword());
            profileDO.setEmail(registerPayload.getEmail());

            profileDO.setCreated_at(new Date());
            profileDO.getUpdated_at().add(new Date());

            //TODO added a default community to

            var noneCommunity = new CommunityDO();
            noneCommunity.setName("None-"+profileDO.getUsername());
            noneCommunity.setCreatorName(profileDO.getUsername());

            noneCommunity.setCreated_at(new Date());
            noneCommunity.getUpdated_at().add(new Date());



            profileDO.getWorkNames().add("DRAFT");

            profileDO = userService.save(profileDO);

            noneCommunity.setCreatorID(profileDO.get_id());
            noneCommunity = communityService.save(noneCommunity);


            profileDO.getCreatedCommunityIDList().add(noneCommunity.get_id());

            //temp??
            profileDO.getJoinedCommunityIDList().add(noneCommunity.get_id());

            profileDO.setNoneCommunity(noneCommunity.get_id());

            userService.update(profileDO);

            Authentication authentication = authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(
                            registerPayload.getUsername(),
                            registerPayload.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

            return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt, userService.findByUsername(registerPayload.getUsername()).get_id()));

        }

        //TODO this to return ProfileTabComponentCOSecurity (which will include additional details)
        //TODO web will have to process this page differently to allow changing password etc
        @PostMapping(path = LOGIN, consumes = {"multipart/form-data", "application/json"})
        public ResponseEntity<?> Login(@Valid @RequestBody LoginRequestPayload loginRequestPayload, BindingResult result) {


            ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
            if (errorMap != null) return errorMap;

            Authentication authentication = authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(
                            loginRequestPayload.getUsername(),
                            loginRequestPayload.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

            var user = userService.findByUsername(loginRequestPayload.getUsername());
            user.getLogins_at().add(new Date());
            userService.update(user);

            return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt, user.get_id()));

        }

    }

    @RestController
    public static class UserViewerController {

        final UserService userService;

        final ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler;
        final ProfileAssembler.ProfileTabCOAssembler profileTabCOAssembler;

        public UserViewerController(UserService userService, ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler, ProfileAssembler.ProfileTabCOAssembler profileTabCOAssembler) {
            this.userService = userService;
            this.profileStreamCOAssembler = profileStreamCOAssembler;
            this.profileTabCOAssembler = profileTabCOAssembler;
        }

        @GetMapping(GET_PROFILE_STREAM_COMPONENT)
        public ResponseEntity<ProfileDTOs.ProfileInfoComponentCO> getProfileStreamComponentCO(@PathVariable ObjectId id, Principal principal) {

            var profileDO = userService.findBy_id(id);

            if (principal == null || !principal.getName().equals(profileDO.getUsername())) {
                //GET PUBLIC VERSION
                return ResponseEntity.ok(profileStreamCOAssembler.assembleDomainToPublicModelView(profileDO));
            } else {
                //GET FULL VERSION
                return ResponseEntity.ok(profileStreamCOAssembler.assembleDomainToFullModelView(profileDO));
            }
        }

        @RequestMapping(GET_PROFILE_TAB_COMPONENT)
        public ResponseEntity<ProfileDTOs.ProfileTabComponentCO> getProfileTabComponentCO(@PathVariable ObjectId id, Principal principal) {
            var profileDO = userService.findBy_id(id);

            if (principal == null || !principal.getName().equals(profileDO.getUsername())) {
                //GET PUBLIC VERSION
                return ResponseEntity.ok(profileTabCOAssembler.assembleDomainToPublicModelView(profileDO));
            } else {
                //GET FULL VERSION
                return ResponseEntity.ok(profileTabCOAssembler.assembleDomainToFullModelView(profileDO));
            }
        }
    }
}
