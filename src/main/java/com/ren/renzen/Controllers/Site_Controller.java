package com.ren.renzen.Controllers;

import com.ren.renzen.Entities.Article;
import com.ren.renzen.Entities.Community;
import com.ren.renzen.Entities.Content;
import com.ren.renzen.Entities.User;
import com.ren.renzen.Repos.Article_Repository;
import com.ren.renzen.Repos.Community_Repository;
import com.ren.renzen.Repos.User_Repository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
public class Site_Controller {

	final User_Repository user_repository;
	final Community_Repository community_repository;
	final Article_Repository article_repository;

	public Site_Controller(User_Repository user_repository, Community_Repository community_repository, Article_Repository article_repository) {
		this.user_repository = user_repository;
		this.community_repository=community_repository;
		this.article_repository = article_repository;
	}

	@RequestMapping()
	public String index() {
		return "index";
	}

	//request mappings map to dispatches



	static class UserNamePassword{
		String username;
		String password;

		//public UserNamePassword(){};
		public UserNamePassword(String username,String password){
			this.username=username;
			this.password=password;
		}

	}

	@PostMapping(path="/register")
	@ResponseBody
	public User Register(@RequestBody UserNamePassword payload){

		if (user_repository.findByUserName(payload.username)!=null){
			return null;
		}

		User user = new User(payload.username,payload.password);
		user_repository.save(user);

		return user;
	}

	@PostMapping(path="/login")
	@ResponseBody
	public User Login(@RequestBody UserNamePassword payload){

		User user=user_repository.findByUserName(payload.username);

		if (user!=null){
			String pass = user.getPassword();
			if (payload.password.equals(user.getPassword())){
				return user; //is returning password... need DTO
			}
		}

		return null;

	}

	//COMMUNITY
	//creates community
	//RequestParam is the User creating the community
	//RequestBody is a new community
	@PostMapping(
			path="/createCommunity",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public Community post_new_community(
			@RequestParam String createdByUserID,
			@RequestBody Community community){

		//check if it already exists
		if (community_repository.findByName(community.getName())!=null){
			return null;
		}

		community.setCreator(createdByUserID);
		community = community_repository.save(community);

		User found_user = user_repository.findById(createdByUserID).orElseThrow(IllegalStateException::new);
		found_user.getCommunities().add("/api/users/"+community.getId()); //hardcoded
		user_repository.save(found_user);

		return community;
	}

	//ARTICLE
	//creates article
	//RequestParam user who created article, community of article
	@PostMapping(
			path="/api/post_new_article",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Article post_new_article(
			@RequestParam String createdByUserID,
			@RequestParam String createdInCommunityID,
			@RequestBody Article new_article)
	{

		//needs to create a content for each section


		//article additions
		new_article.setAuthor(createdByUserID);
		new_article.setCommunity(createdInCommunityID);
		new_article = article_repository.save(new_article);

		User found_user = user_repository.findById(createdByUserID).orElseThrow(IllegalStateException::new);
		Community found_community = community_repository.findById(createdInCommunityID).orElseThrow(IllegalStateException::new);

		//add article to creating user and host community
		found_user.getArticles().add(new_article.getId());
		found_community.getArticles().add(new_article.getId());

		return new_article;
	}

	//USER
	@PostMapping(
			path="/api/post_new_user",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User post_new_user(@RequestBody User user){
		user = user_repository.save(user);
		return user;
	}




//	@RequestMapping("/api/post_new_user")
//	public String post_new_user(){
//		return "";
//	}
//
//	@RequestMapping("/api/post_new_article")
//	public String post_new_article(){
//
//		//creates a new article
//		//add the article to the current users article array
//		//adds article to community
//
//		return "";
//	}
//
//	@RequestMapping("/api/post_new_content")
//	public String post_new_content(){
//		return "";
//	}
}



//---------------------------------

//old stuff

//public class Content_Controller {
//
////    private Content_Repository mongoRepo;
////
////    public Content_Controller(Content_Repository mongoRepo){
////        this.mongoRepo=mongoRepo;
////    }
//
////    @GetMapping//(path="/Notes")
////    public Iterable<X_Note> getNotes(){
////        return this.mongoRepo.findAll();
////    }
//
////    @RequestMapping(value="/pageName")
////    public List<X_Note> getNotesByPage(@RequestParam("pageName") String pageName){
////        System.out.println("proc");
////        return this.mongoRepo.findX_NotesByPageSource(pageName);
////    }
////
////    @GetMapping( value="/a")
////    public @ResponseBody Iterable<Content> getNotesByPage(){
////
////        //return ("hi");
////        return mongoRepo.findAll();
////
////    }
////
////    @GetMapping(value="/pageSource")
////    public @ResponseBody Iterable<Content> getNotesByPage(@RequestParam("pageSource") String pageSource){
////        System.out.println("proc");
////        //return ("HI");
////        return this.mongoRepo.findX_NotesByPageSource(pageSource);
////    }
////
////    @GetMapping(value="/section")
////    public @ResponseBody Iterable<Content> getNotesByPage(
////            @RequestParam("pageSource") String pageSource,
////            @RequestParam("noteType") String noteType ){
////        System.out.println("proc");
////        //return ("HI");
////        return this.mongoRepo.findX_NotesByPageSourceAndNoteType(pageSource,noteType);
////    }
////
////    @GetMapping(value="/getChildren")
////    public @ResponseBody Iterable<Content> getByParent(
////            @RequestParam("parent") String parent){
////        return this.mongoRepo.findX_NotesByParentNote(parent);
////    }
////
//
//    //mapping to retrieve user content on page
//    //@GetMapping(path="x_Notes/{id}")
////    @RestResource(path="notes")
////    public List<X_Note> getSelectNotes(@Param("name") String pageId){
////        System.out.println("retrieving notes by page id");
////        return this.mongoRepo.findX_NotesByPageSource(pageId);
////    }
//
////    @PatchMapping(path="/{id}")
////    public X_Note updateNote(@PathVariable(value="id") String noteID){
////        mongoRepo.save(mongoRepo.findX_NoteById(noteID));
////        return mongoRepo.findX_NoteById(noteID);
////    }
//
//
////    @PatchMapping(path="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
////    public void updateNote(
////            @RequestBody Map<String,X_Note> updates,
////            @PathVariable(value = "id") String id){
////
////        mongoRepo.
////
////        this.mongoRepo.save(updates,id);
////        //X_Note n = mongoRepo.findX_NoteById(id);
////        //mongoRepo.save(note);
////    }
//
////    @PostMapping()
////    public String postNote(@RequestBody X_Note note){
////
////        X_Note x = mongoRepo.save(note);
////
////        x.getContent();
////
////        return ("Note Content: "+x.getContent());
////    }
//
//
//}