//RECEIVABLE CLASSES

function validateClass(obj, orig) {
  Object.keys(obj).forEach((key) => {
    if (obj[key] === undefined) {
      alert(key + " in class not defined");
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

    validateClass(this, obj);
  }
}

export class ArticleInfoComponentCO {
  constructor(obj) {}
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

    validateClass(this, obj);
  }
}

export class HomeTabComponentCO {
  constructor() {}
}

export class ProfileInfoComponentCO {
  constructor() {}
}

export class ProfileTabComponentCO {
  constructor() {}
}

//SEND-ABLE CLASSES

export class UpdateArticlePayload {
  constructor() {}
}

export class RegisterPayload {
  constructor() {}
}

export class LoginRequestPayload {
  constructor() {}
}

export class JWTLoginSuccessResponse {
  constructor() {}
}

export class addBookmarkPayload {
  constructor() {}
}

export class addCommentPayload {
  constructor() {}
}

export class CreateCommunityPayload {
  constructor() {}
}

export class JoinCommunityPayload {
  constructor() {}
}

export class respondToPollPayload {
  constructor() {}
}
