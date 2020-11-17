export function TabPane_StateToProps(state) {
  return {
    selectedTab: state.reducer.selectedTab,
    open: state.reducer.tabs.open,
    streamPage: state.reducer.streamPage,
  };
}

//added ownProps
export function ArticlePage_StateToProps(state, ownProps) {
  return {
    selectedTab: state.reducer.selectedTab,
    state: state.reducer,
    user: state.reducer.user,
    //article: state.reducer.tabs.open[state.reducer.tabs.open.length - 1],
    article: state.reducer.tabs.open.find(
      (openArticle) => openArticle.id === ownProps.id
    ),
  };
}

export function Box_StateToProps(state, ownProps) {
  return {
    content: state.reducer.homeTabData.stream_articles.find(
      (article) => article._id === ownProps.single._id
    ),
    //
    // content:state.reducer.homeTabData.stream_articles
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
    errors: state.reducer.errors,
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
