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

@RestController
@CrossOrigin("*")
public class Home_Controller {

	final User_Repository user_repository;
	final Community_Repository community_repository;
	final Article_Repository article_repository;

	public Home_Controller(User_Repository user_repository, Community_Repository community_repository, Article_Repository article_repository) {
		this.user_repository = user_repository;
		this.community_repository=community_repository;
		this.article_repository = article_repository;
	}

	@RequestMapping()
	public String index() {
		return "index";
	}

	//request mappings map to dispatches



	//COMMUNITY
	//creates community
	//RequestParam is the User creating the community
	//RequestBody is a new community
	@PostMapping(
			path="/api/post_new_community",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Community post_new_community(
			@RequestParam String createdByUserID,
			@RequestBody Community new_community){

		new_community.setCreator(createdByUserID);
		new_community = community_repository.save(new_community);

		User found_user = user_repository.findById(createdByUserID).orElseThrow(IllegalStateException::new);
		found_user.getCommunities().add("/api/users/"+new_community.getId()); //hardcoded
		user_repository.save(found_user);

		return new_community;
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

