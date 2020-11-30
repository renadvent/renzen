package com.ren.renzen.Controllers;

public class CONTROLLER_PATHS {

    public static class Article{

        public static final String PUBLISH_ARTICLE = "/publishArticle/{id}";
        public static final String UNPUBLISH_ARTICLE =  "/unpublishArticle/{id}";
        public static final String DELETE_ARTICLE = "/deleteArticle/{id}";
        public static final String LIKE_ARTICLE = "/likeArticle/{id}";
        public static final String DISLIKE_ARTICLE = "/dislikeArticle/{id}";

        //used by site client
        public static final String CREATE_ARTICLE_FROM_SITE = "/createArticle";

        //used by desktop app
        public static final String NEW_CREATE_ARTICLE = "/newCreateArticle";



        public static final String OPEN_ARTICLE_DRAFT_FROM_APP = "/OPEN_ARTICLE_DRAFT_FROM_APP";
        public static final String CREATE_ARTICLE_DRAFT_FROM_APP = "/CREATE_ARTICLE_DRAFT_FROM_APP";


        public static final String DELETE_IMAGE_FROM_PROFILE_COMPAT = "/deleteImageFromProfile/{link}";
        public static final String ADD_IMAGE_TO_PROFILE_COMPAT = "/addImage";

        public static final String GET_ARTICLE_STREAM_COMPONENTCO = "/getArticleStreamComponentCO/{id}";
        public static final String GET_ARTICLE_TAB_COMPONENTCO = "/getArticleTabComponentCO/{id}";

        public static final String ADD_COMMENT = "/addComment/{id}";
        public static final String RESPOND_TO_POLL = "/respondToPoll/{id}";

        public static final String GET_EXPLORE_FEED = "/getExploreFeed/{id}";
        public static final String GET_YOUR_FEED = "/getYourFeed/{id}";
        public static final String GET_HOME_STREAMS = "/getHomeStreams/{page}";

        public static final String REFRESH_COMMENTS = "/refreshComments/{id}";

    }

    public static class Community {

        public static final String JOIN_COMMUNITY = "/joinCommunity";
        public static final String CREATE_COMMUNITY = "/createCommunity";
        public static final String GET_COMMUNITY_STREAM_COMPONENT = "/getCommunityStreamComponentCO/{id}";
        public static final String GET_COMMUNITY_TAB_COMPONENT = "/getCommunityTabComponent/{id}";

    }

    public static class User{

        public static final String ADD_BOOKMARK = "/addBookmark";
        public static final String REGISTER = "/register";
        public static final String LOGIN = "/login";

        public static final String GET_PROFILE_STREAM_COMPONENT = "/getProfileStreamComponentCO/{id}";
        public static final String GET_PROFILE_TAB_COMPONENT = "/getProfileTabComponentCO/{id}";

    }

    public static class Admin{

        public static final String GET_ADMIN_ARTICLES = "/getAdminArticles";
        public static final String GET_ADMIN_COMMUNITIES = "/getAdminCommunities";
        public static final String GET_ADMIN_PROFILES = "/getAdminProfiles";

    }

}
