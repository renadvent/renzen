//RECEIVABLE CLASSES

export class ArticleTabComponentCO {
    
  constructor(obj) {
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
  }
}

export class ArticleInfoComponentCO {
  constructor(obj) {
  }
}

export class ArticleSectionCO {
  constructor() {}
}

export class CommunityInfoComponentCO {
  constructor() {}
}

export class CommunityTabComponentCO {
  constructor() {}
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
