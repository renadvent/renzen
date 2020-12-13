package com.ren.renzen.Converters;

import com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER_SUPPORT;
import com.ren.renzen.Repositories.ArticleRepository;
import com.ren.renzen.Repositories.UserRepository;
import com.ren.renzen.ResourceObjects.CommandObjects.ArticleDTOs;
import com.ren.renzen.ResourceObjects.DomainObjects.ArticleDO;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.ImageService;
import com.ren.renzen.Services.Interfaces.UserService;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ArticleConverter {


    @Component
    public static class Comment_to_CommentCO{

        final UserService userService;
        final ArticleService articleService;

        public Comment_to_CommentCO(UserService userService, ArticleService articleService) {
            this.userService = userService;
            this.articleService = articleService;
        }

        @Synchronized
        public ArticleDTOs.CommentCO convert (ArticleDO.Comment comment){
            var commentCO = new ArticleDTOs.CommentCO();

            commentCO.setLikes(comment.getLikes());
            commentCO.setDislikes(comment.getDislikes());
            commentCO.setUserDislikeIDs(comment.getUserDislikeIDs());
            commentCO.setUserLikeIDs(comment.getUserLikeIDs());
            commentCO.setComment(comment.getComment());
            commentCO.setAuthor(comment.getAuthor());
            commentCO.setAuthorName(comment.getAuthorName());

            return commentCO;

        }



    }

    @Component
    public static class ArticleDO_to_ArticleStreamComponentCO extends DOMAIN_VIEW_CONVERTER_SUPPORT<ArticleDO, ArticleDTOs.ArticleInfoComponentCO> {

        final UserService userService;
        final ArticleService articleService;
        final ImageService imageService;
        final CommunityService communityService;
        final ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

        public ArticleDO_to_ArticleStreamComponentCO(UserService userService, ArticleService articleService, ImageService imageService, CommunityService communityService, ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO) {
            this.userService = userService;
            this.articleService = articleService;
            this.imageService = imageService;
            this.communityService = communityService;
            this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
        }

        @Synchronized
        @Override
        public ArticleDTOs.ArticleInfoComponentCO convertDomainToPublicView(ArticleDO source) {

            final ArticleDTOs.ArticleInfoComponentCO co = new ArticleDTOs.ArticleInfoComponentCO();
            co.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);


            //THIS IS SET IN ASSEMBLER
            //co.setCommentsDTO();


            co.set_id(source.get_id().toHexString());
            co.setObjectId(source.get_id());

            co.setCreatorID(source.getCreatorID());
            co.setCommunityID(source.getCommunityID());

            co.setLikes(source.getLikes());
            co.setDislikes(source.getDislikes());
            co.setUserLikeIDs(source.getUserLikeIDs());
            co.setUserDislikeIDs(source.getUserDislikeIDs());

            co.setIsDraft(source.getIsDraft());
            co.setArticleName(source.getArticleName());
            co.setWorkName(source.getWorkName());

            co.setTagList(source.getTagList());
            co.setComments(source.getComments());
            co.setPollOptions(source.getPollOptions());

            try {
                String name = source.getPostImageURL().substring(source.getPostImageURL().lastIndexOf('/') + 1);

                co.setPostImageURL(imageService.generateSAS(name));
            } catch (Exception e) {
                co.setPostImageURL(null);
            }

            if (source.getCommunityID()!=null){
                co.setCommunityName(communityService.findBy_id(source.getCommunityID()).getName());
            }

            var author = userService.findBy_id(source.getCreatorID());
            co.setCreatorName(author.getUsername());

            co.setOtherPostsInWork(
                    articleService.findAllByCreatorIDAndWorkName(author.get_id(), co.getWorkName())
                            .stream().filter(articleDO -> !articleDO.getIsDraft()).map(ArticleDO::get_id).collect(Collectors.toList())
            );

            co.setOtherPostsInWorkHex(co.getOtherPostsInWork().stream().map(ObjectId::toHexString).collect(Collectors.toList()));
            co.setProfileInfoComponentCO(profileDO_to_profileStreamComponentCO.convertDomainToPublicView(author));

            return co;
        }

        @Synchronized
        @Override
        public ArticleDTOs.ArticleInfoComponentCO convertDomainToFullView(ArticleDO source) {

            //get all public info
            final var co = convertDomainToPublicView(source);

            co.setACCESS_TYPE(ACCESS_TYPE_FULL);

            //get private info

            return co;
        }

    }

    /**
     * Converts ArticleDO to ArticleComponentCO
     * which is a format that allows the React application to render the Article
     */

    @Component
    public static class ArticleDO_to_ArticleTabComponentCO extends DOMAIN_VIEW_CONVERTER_SUPPORT<ArticleDO, ArticleDTOs.ArticleTabComponentCO> {

        final UserRepository userRepo;
        final ArticleRepository articleRepo;
        final ArticleService articleService;
        final UserService userService;
        final CommunityService communityService;
        final ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
        final ArticleSectionDO_to_ArticleSectionCO articleSectionDO_to_articleSectionCO;

        final ImageService imageService;


        @Autowired
        public ArticleDO_to_ArticleTabComponentCO(UserRepository repo, ArticleRepository articleRepo, ArticleService articleService, UserService userService, CommunityService communityService, ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDOtoprofileStreamComponentCO, ArticleSectionDO_to_ArticleSectionCO articleSectionDO_to_articleSectionCO, ImageService imageService) {
            this.userRepo = repo;
            this.articleRepo = articleRepo;
            this.articleService = articleService;
            this.userService = userService;
            this.communityService = communityService;
            profileDO_to_profileStreamComponentCO = profileDOtoprofileStreamComponentCO;
            this.articleSectionDO_to_articleSectionCO = articleSectionDO_to_articleSectionCO;
            this.imageService = imageService;
        }

        @Synchronized
        ArticleDTOs.ArticleTabComponentCO common(ArticleDO source) {

            ArticleDTOs.ArticleTabComponentCO co = new ArticleDTOs.ArticleTabComponentCO();

            co.set_id(source.get_id().toHexString());
            co.setObjectId(source.get_id());

            co.setCreatorID(source.getCreatorID());
            co.setCommunityID(source.getCommunityID());

            co.setLikes(source.getLikes());
            co.setDislikes(source.getDislikes());
            co.setUserLikeIDs(source.getUserLikeIDs());
            co.setUserDislikeIDs(source.getUserDislikeIDs());

            co.setIsDraft(source.getIsDraft());
            co.setArticleName(source.getArticleName());
            co.setWorkName(source.getWorkName());

            co.setTagList(source.getTagList());
            co.setComments(source.getComments());
            co.setPollOptions(source.getPollOptions());

            try {
                String name = source.getPostImageURL().substring(source.getPostImageURL().lastIndexOf('/') + 1);
                co.setPostImageURL(imageService.generateSAS(name));
            } catch (Exception e) {
                co.setPostImageURL(null);
            }

            var author = userService.findBy_id(source.getCreatorID());
            co.setCreatorName(author.getUsername());

            co.setOtherPostsInWork(
                    articleService.findAllByCreatorIDAndWorkName(author.get_id(), co.getWorkName())
                            .stream().filter(articleDO -> !articleDO.getIsDraft()).map(ArticleDO::get_id).collect(Collectors.toList())
            );

            co.setOtherPostsInWorkHex(co.getOtherPostsInWork().stream().map(ObjectId::toHexString).collect(Collectors.toList()));
            co.setProfileInfoComponentCO(profileDO_to_profileStreamComponentCO.convertDomainToPublicView(author));
//
            if (source.getCommunityID()!=null){
                co.setCommunityName(communityService.findBy_id(source.getCommunityID()).getName());
            }

            for (ArticleDO.ArticleSectionDO articleSectionDO : source.getArticleSectionDOList()) {
                co.getArticleSectionCOList().add(articleSectionDO_to_articleSectionCO.convert(articleSectionDO));
            }

            return co;
        }

        @Synchronized
        @Nullable
        @Override
        public ArticleDTOs.ArticleTabComponentCO convertDomainToPublicView(ArticleDO source) {
            final ArticleDTOs.ArticleTabComponentCO co = common(source);
            co.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);
            return co;
        }

        @Synchronized
        @Nullable
        @Override
        public ArticleDTOs.ArticleTabComponentCO convertDomainToFullView(ArticleDO source) {
            final ArticleDTOs.ArticleTabComponentCO co = common(source);
            co.setACCESS_TYPE(ACCESS_TYPE_FULL);
            return co;
        }
    }

    @Component
    public static class ArticleSectionDO_to_ArticleSectionCO implements Converter<ArticleDO.ArticleSectionDO, ArticleDTOs.ArticleSectionCO> {

        final ImageService imageService;

        public ArticleSectionDO_to_ArticleSectionCO(ImageService imageService) {
            this.imageService = imageService;
        }

        @Synchronized
        @Nullable
        @Override
        public ArticleDTOs.ArticleSectionCO convert(ArticleDO.ArticleSectionDO source) {
            ArticleDTOs.ArticleSectionCO co = new ArticleDTOs.ArticleSectionCO();
            co.setHeader(source.getHeader());
            co.setBody(source.getBody());

            return co;
        }
    }
}
