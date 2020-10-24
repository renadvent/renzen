package com.ren.renzen.Controllers;

import com.ren.renzen.Converters.*;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.*;
import com.ren.renzen.Payload.JWTLoginSuccessResponse;
import com.ren.renzen.Payload.LoginRequest;
import com.ren.renzen.Payload.RegisterPayload;
import com.ren.renzen.Security.JwtTokenProvider;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import com.ren.renzen.Services.MapValidationErrorService;
import com.ren.renzen.Validator.UserNamePasswordValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.ren.renzen.additional.KEYS.TOKEN_PREFIX;

@RestController
public class UserEditorController {

    //services
    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

    //converters
    final ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;
    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;
    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;
    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    //assemblers
    final ArticleTabCOAssembler articleTabCOAssembler;
    final ProfileStreamCOAssembler profileStreamCOAssembler;
    final ProfileTabCOAssembler profileTabCOAssembler;
    final CommunityTabCOAssembler communityTabCOAssembler;
    final CommunityStreamCOAssembler communityStreamCOAssembler;
    final ArticleStreamCOAssembler articleStreamCOAssembler;

    //Error service
    final MapValidationErrorService mapValidationErrorService;

    //Validation Service
    final UserNamePasswordValidator userNamePasswordValidator;

    //Token Provider
    final JwtTokenProvider jwtTokenProvider;

    //Authentication Manager
    final AuthenticationManager authenticationManager;

    public UserEditorController(UserService userService, ArticleService articleService, CommunityService communityService, ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler, MapValidationErrorService mapValidationErrorService, UserNamePasswordValidator userNamePasswordValidator, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
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

    @PostMapping(path = "/register")
    public ResponseEntity<?> Register(@Valid @RequestBody RegisterPayload registerPayload, BindingResult result) {

        userNamePasswordValidator.validate(registerPayload, result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        //temp
        if (!registerPayload.getPassword().equals(registerPayload.getConfirmPassword())
        ||
        userService.findByEmail(registerPayload.getEmail()).isPresent()
        ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var profileDO = new ProfileDO();
        profileDO.setUsername(registerPayload.getUsername());
        profileDO.setPassword(registerPayload.getPassword());
        profileDO.setEmail(registerPayload.getEmail());

        userService.save(profileDO);

        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        registerPayload.getUsername(),
                        registerPayload.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        //pass back token
//        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt,userService.findByUsername(registerPayload.getUsername()).get_id()));


//        return new ResponseEntity<>(profileTabCOAssembler
//                .assembleDomainToFullModelView(
//                        userService
//                                .save
//                                        (profileDO)
//                ),
//                HttpStatus.CREATED
//        );
    }

    //TODO this to return ProfileTabComponentCOSecurity (which will include additional details)
    //TODO web will have to process this page differently to allow changing password etc
    @PostMapping(path = "/login", consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<?> Login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {


        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        //pass back token
//        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt,userService.findByUsername(loginRequest.getUsername()).get_id()));

        //return ResponseEntity.ok(profileTabCOAssembler.toModel(userService.findProfileDOByNameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())));
    }

}