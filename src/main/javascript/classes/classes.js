//RECEIVABLE CLASSES

function validateClass(obj, orig) {
  Object.keys(obj).forEach((key) => {
    if (obj[key] === undefined) {
      alert("VALIDATION ERROR: " + key + " in class not defined");
      console.log("VALIDATE FAIL-----------------------------");
      console.log(obj);
      console.log(orig);
      console.log("------------------------------------------");
    } else {
      return false;
    }
  });

  return true;
}

export class ArticleTabComponentCO {
  constructor(obj) {
    this.ACCESS_TYPE = obj.access_TYPE;

    this._id = obj._id;
    this.objectId = obj.objectId;

    this.creatorID = obj.creatorID;
    this.communityID = obj.communityID;

    this.likes = obj.likes;
    this.dislikes = obj.dislikes;
    this.userLikeIDs = obj.userLikeIDs;
    this.userDislikeIDs = obj.userDislikeIDs;

    this.isDraft = obj.isDraft;
    this.articleName = obj.articleName;
    this.workName = obj.workName;

    this.tagList = obj.tagList;
    this.comments = obj.comments;
    this.pollOptions = obj.pollOptions;

    this.articleSectionDOList = obj.articleSectionDOList;

    this.postImageURL = obj.postImageURL;

    this.created_at = obj.created_at;
    this.updated_at = obj.updated_at;

    this.articleSectionCOList = obj.articleSectionCOList;
    this.profileInfoComponentCO = obj.profileInfoComponentCO;
    this.otherPostsInWork = obj.otherPostsInWork;
    this.otherPostsInWorkHex = obj.otherPostsInWorkHex;
    this.creatorName = obj.creatorName;
    this.communityName = obj.communityName;

    this._links = obj._links;

    validateClass(this, obj);
  }
}

export class ArticleInfoComponentCO {
  constructor(obj) {
    this.ACCESS_TYPE = obj.access_TYPE;

    this._id = obj._id;
    this.objectId = obj.objectId;

    this.creatorID = obj.creatorID;
    this.communityID = obj.communityID;

    this.likes = obj.likes;
    this.dislikes = obj.dislikes;
    this.userLikeIDs = obj.userLikeIDs;
    this.userDislikeIDs = obj.userDislikeIDs;

    this.isDraft = obj.isDraft;
    this.articleName = obj.articleName;
    this.workName = obj.workName;

    this.tagList = obj.tagList;
    this.comments = obj.comments;
    this.pollOptions = obj.pollOptions;

    this.articleSectionDOList = obj.articleSectionDOList;

    this.postImageURL = obj.postImageURL;

    this.created_at = obj.created_at;
    this.updated_at = obj.updated_at;

    this.articleSectionCOList = obj.articleSectionCOList;
    this.profileInfoComponentCO = obj.profileInfoComponentCO;
    this.otherPostsInWork = obj.otherPostsInWork;
    this.otherPostsInWorkHex = obj.otherPostsInWorkHex;
    this.creatorName = obj.creatorName;
    this.communityName = obj.communityName;

    this._links = obj._links;

    validateClass(this, obj);
  }
}

export class ArticleSectionCO {
  constructor(obj) {
    this._id = obj._id;
    this.ACCESS_TYPE = obj.access_TYPE;
    this.objectId = obj.objectId;
    this.header = obj.header;
    this.body = obj.body;
    this.authorID = obj.authorID;
    this.imageID = obj.imageID;

    validateClass(this, obj);
  }
}

export class CommunityInfoComponentCO {
  constructor(obj) {
    this.ACCESS_TYPE = obj.access_TYPE;
    this._id = obj._id;
    this.objectId = obj.objectId;
    this.name = obj.name;
    this.profileInfoComponentCOList = obj.profileInfoComponentCOList;
    this.articleInfoComponentCOList = obj.articleInfoComponentCOList;

    this._links = obj._links;

    validateClass(this, obj);
  }
}

export class CommunityTabComponentCO {
  constructor(obj) {
    this.ACCESS_TYPE = obj.access_TYPE;
    this._id = obj._id;
    this.objectId = obj.objectId;
    this.name = obj.name;
    this.profileInfoComponentCOList = obj.profileInfoComponentCOList;
    this.articleInfoComponentCOList = obj.articleInfoComponentCOList;

    this.numberOfUsers = obj.numberOfUsers;
    this.numberOfArticles = obj.numberOfArticles;

    this._links = obj._links;

    validateClass(this, obj);
  }
}

export class HomeTabComponentCO {
  constructor(obj) {
    this.ACCESS_TYPE = obj.access_TYPE;
    this._id = obj._id;
    this.objectId = obj.objectId;

    this.articleInfoComponentCOList = obj.articleInfoComponentCOList;
    this.profileInfoComponentCOList = obj.profileInfoComponentCOList;
    this.communityInfoComponentCOList = obj.communityInfoComponentCOList;

    this._links = obj._links;

    validateClass(this, obj);
  }
}

export class ProfileInfoComponentCO {
  constructor(obj) {
    this.ACCESS_TYPE = obj.access_TYPE;
    this._id = obj._id;
    this.objectId = obj.objectId;
    this.name = obj.name;
    this.profilePictureLink = obj.profilePictureLink;

    this._links = obj._links;

    validateClass(this, obj);
  }
}

export class ProfileTabComponentCO {
  constructor(obj) {
    this.ACCESS_TYPE = obj.access_TYPE;
    this._id = obj._id;
    this.objectId = obj.objectId;
    this.name = obj.name;
    this.profilePictureLink = obj.profilePictureLink;

    this.articleIDList = obj.articleIDList;
    this.communityIDList = obj.communityIDList;
    this.articleDraftIDList = obj.articleDraftIDList;

    this.articleDraftInfoComponentCOS = obj.articleDraftInfoComponentCOS;
    this.articleInfoComponentCOS = obj.articleInfoComponentCOS;
    this.communityInfoComponentCOS = obj.communityInfoComponentCOS;
    this.articleBookmarksCM = obj.articleBookmarksCM;

    this.workNames = obj.workNames;

    this.numberOfArticles = obj.numberOfArticles;
    this.numberOfDrafts = obj.numberOfDrafts;
    this.numberOfCommunities = obj.numberOfCommunities;

    this._links = obj._links;

    validateClass(this, obj);
  }
}

export class JWTLoginSuccessResponse {
  constructor(obj) {}
}

//SEND-ABLE CLASSES

export class UpdateArticlePayload {
  constructor() {
    this.articleName = "";
    this.communityID = "";
    this.articleSectionDOList = [];
    this.workName = "";
    this.tags = [];
    this.pollOptions = [];
  }
}

export class RegisterPayload {
  constructor() {
    this.username = "";
    this.password = "";
    this.confirmPassword = "";
    this.email = "";
  }
}

export class LoginRequestPayload {
  constructor() {
    this.username = "";
    this.password = "";
  }
}

export class addBookmarkPayload {
  constructor() {
    this.articleId = "";
  }
}

export class addCommentPayload {
  constructor() {
    this.comment = "";
  }
}

export class CreateCommunityPayload {
  constructor() {
    this.name = "";
  }
}

export class JoinCommunityPayload {
  constructor() {
    this.communityId = "";
  }
}

export class respondToPollPayload {
  constructor(obj) {
    this.option = "";
  }
}
