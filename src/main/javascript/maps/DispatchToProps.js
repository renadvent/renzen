import * as store from "../actions/Store_Actions";

export function HomePageStream_dtp(dispatch) {
  return {};
}

export const TabPane_mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_logOut: () => dispatch(store.DISPATCH_logOut()),
    DISPATCH_init: () => dispatch(store.DISPATCH_init()),
    DISPATCH_openCreateArticleTab: (comid) =>
      dispatch(store.DISPATCH_openCreateArticleTab(comid)),
  };
};

export function ArticlePage_mapDispatchToProps(dispatch) {
  return {
    DISPATCH_addComment: (id, comment, uuid) =>
      dispatch(store.DISPATCH_addComment(id, comment, uuid)),

    DISPATCH_addBookmark: (userId, articleId, name) =>
      dispatch(store.DISPATCH_addBookmark(userId, articleId, name)),

    DISPATCH_likeArticle: (articleId, uuid) =>
      dispatch(store.DISPATCH_likeArticle(articleId, uuid)),
    DISPATCH_dislikeArticle: (articleId, uuid) =>
      dispatch(store.DISPATCH_dislikeArticle(articleId, uuid)),
  };
}

export function Bookmarks_mapDispatchToProps(dispatch) {
  return {
    DISPATCH_openArticle: (url) => dispatch(store.DISPATCH_openArticle(url)),
  };
}

export function BookmarksTab_mapDispatchToProps(dispatch) {
  return {
    DISPATCH_removeOpenTabById: (id) =>
      dispatch(store.DISPATCH_removeOpenTabById(id)),
  };
}

export function CommunityPage_mapDispatchToProps(dispatch) {
  return {
    DISPATCH_joinCommunity: (userId, communityId) =>
      dispatch(
        store.DISPATCH_joinCommunity({
          userId: userId,
          communityId: communityId,
        })
      ),
    DISPATCH_createArticle: () => dispatch(store.DISPATCH_createArticle()),
    DISPATCH_openArticle: (url) => dispatch(store.DISPATCH_openArticle(url)),
    DISPATCH_openCreateArticleTab: (comid) =>
      dispatch(store.DISPATCH_openCreateArticleTab(comid)),
  };
}

export function CreateArticlePage_mapDispatchToProps(dispatch) {
  return {
    // DISPATCH_createArticle:
    //     (payload, user, community, sectionData)
    //         =>
    //     dispatch(
    //         store.DISPATCH_createArticle(payload, user, community, sectionData)
    //     ),

    DISPATCH_createArticle: (payload, user, community, sectionData, id) =>
      dispatch(
        store.DISPATCH_createArticle(payload, user, community, sectionData, id)
      ),
    DISPATCH_removeOpenTabById: (id) =>
      dispatch(store.DISPATCH_removeOpenTabById(id)),
  };
}

export function Header_mapDispatchToProps(dispatch) {
  return {
    DISPATCH_logOut: () => dispatch(store.DISPATCH_logOut()),
    DISPATCH_openUser: (url) => dispatch(store.DISPATCH_openUser(url)),
  };
}

export const HomePage_mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_replacePost: (originalID, currentID, replacementID) =>
      dispatch(
        store.DISPATCH_replacePost(originalID, currentID, replacementID)
      ),

    DISPATCH_addComment: (id, comment, uuid) =>
      dispatch(store.DISPATCH_addComment(id, comment, uuid)),

    DISPATCH_createArticle: () => dispatch(store.DISPATCH_createArticle()),

    DISPATCH_openCommunity: (url) =>
      dispatch(store.DISPATCH_openCommunity(url)),
    DISPATCH_openUser: (url) => dispatch(store.DISPATCH_openUser(url)),
    DISPATCH_openArticle: (url) => dispatch(store.DISPATCH_openArticle(url)),

    DISPATCH_logIn: (username, password) =>
      dispatch(
        store.DISPATCH_logIn({ username: username, password: password })
      ),
    DISPATCH_register: (username, password) =>
      dispatch(
        store.DISPATCH_register({ username: username, password: password })
      ),

    DISPATCH_createCommunity: (user, payload) =>
      dispatch(store.DISPATCH_createCommunity(user, payload)),

    DISPATCH_likeArticle: (articleId, uuid) =>
      dispatch(store.DISPATCH_likeArticle(articleId, uuid)),
    DISPATCH_dislikeArticle: (articleId, uuid) =>
      dispatch(store.DISPATCH_dislikeArticle(articleId, uuid)),
  };
};

export const LoginRegister_mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_logIn: (username, password) =>
      dispatch(
        store.DISPATCH_logIn({ username: username, password: password })
      ),
    DISPATCH_register: (username, password, confirmPassword, email) => {
      dispatch(
        store.DISPATCH_register({
          username: username,
          password: password,
          confirmPassword: confirmPassword,
          email: email,
        })
      );
    },

    DISPATCH_createCommunity: (user, payload) =>
      dispatch(store.DISPATCH_createCommunity(user, payload)),
    DISPATCH_logOut: () => dispatch(store.DISPATCH_logOut()),
  };
};

export const PageTab_mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_removeOpenTabById: (id) =>
      dispatch(store.DISPATCH_removeOpenTabById(id)),
  };
};

export const ProfilePage_mapDispatchToProps = (dispatch) => {
  return {
    dispatch: (action) => dispatch(action),
    DISPATCH_logOut: () => dispatch(store.DISPATCH_logOut()),
    DISPATCH_openArticle: (url) => dispatch(store.DISPATCH_openArticle(url)),
    DISPATCH_createCommunity: (creatorID, name) =>
      dispatch(
        store.DISPATCH_createCommunity({
          creatorID: creatorID,
          name: name,
        })
      ),
    DISPATCH_openCommunity: (url) =>
      dispatch(store.DISPATCH_openCommunity(url)),
  };
};

export const Spotlight_mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_openCommunity: (url) =>
      dispatch(store.DISPATCH_openCommunity(url)),
    DISPATCH_openUser: (url) => dispatch(store.DISPATCH_openUser(url)),
    DISPATCH_openArticle: (url) => dispatch(store.DISPATCH_openArticle(url)),
  };
};
