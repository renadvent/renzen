export const initialState = {
  errors: "",
  selectedTab: "",

  user: {
    logged_in: false,
    name: "",
    id: "",
    //url:"",
    communities: [],
    articles: [],
    study_guides: [],
    user_data: {},
    bookmarks: [],
  },

  spotlight: {
    articles: [],
    communities: [],
  },

  tabs: {
    open: [],
  },

  streamPage: 0,

  data: [],

  homeTabData: {
    stream_users: [],
    stream_communities: [],
    stream_articles: [],
    stream_articles_UUID: [],
  },
};
