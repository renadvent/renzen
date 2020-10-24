export function TabPane_StateToProps(state) {
  return {
    open: state.reducer.tabs.open,
  };
}

export function ArticlePage_StateToProps(state) {
  return {
    state: state.reducer,
    user: state.reducer.user,
  };
}

export function Bookmarks_StateToProps(state) {
  return {
    state: state.reducer,
    user: state.reducer.user,
  };
}

export function BookmarksTab_StateToProps(state) {
  return {};
}

export function CommunityPage_StateToProps(state) {
  return {
    user: state.reducer.user,
  };
}

export function CreateArticlePage_StateToProps(state) {
  return {
    open: state.reducer.tabs.open,
    user_communities: state.reducer.user.communities,
    user: state.reducer.user,
  };
}

export function Header_StateToProps(state) {
  return {
    user: state.reducer.user,
  };
}

export function HomePage_StateToProps(state) {
  return {
    loadedCommunities: state.reducer.homeTabData.stream_communities,
    loadedArticles: state.reducer.homeTabData.stream_articles,
    loadedUsers: state.reducer.homeTabData.stream_users,
  };
}

export function LoginRegister_StateToProps(state) {
  return {
    isLoggedIn: state.reducer.isLoggedIn,
    user: state.reducer.user,
  };
}

export function PageTab_StateToProps(state) {
  return {};
}

export function ProfilePage_StateToProps(state) {
  return {
    user: state.reducer.user,
  };
}

export function Spotlight_StateToProps(state) {
  return {
    loadedCommunities: state.reducer.homeTabData.stream_communities,
    loadedArticles: state.reducer.homeTabData.stream_articles,
    loadedUsers: state.reducer.homeTabData.stream_users,

    user: state.user,
    state: state,
  };
}
