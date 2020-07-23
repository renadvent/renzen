package com.ren.renzen.Controllers;

import com.ren.renzen.Entities.Article;
import com.ren.renzen.Entities.Community;
import com.ren.renzen.Entities.User;
import com.ren.renzen.Repos.Article_Repository;
import com.ren.renzen.Repos.Community_Repository;
import com.ren.renzen.Repos.User_Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

	//returns homepage
	@RequestMapping()
	public String index() {
		return "index";
	}

	//allows register
	@PostMapping(path="/register")
	@ResponseBody
	public User Register(@RequestBody UserNamePassword payload){
		if (user_repository.findByUserName(payload.username)!=null){
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "username is taken");
		}
		User user = new User(payload.username,payload.password);
		user_repository.save(user);
		return user;
	}

	//allows login
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
			path="/createArticle",
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

	//helper classes

	static class UserNamePassword{
		String username;
		String password;

		//public UserNamePassword(){};
		public UserNamePassword(String username,String password){
			this.username=username;
			this.password=password;
		}
	}


	class Hydrate {
		Article article (Article article){
			//article.getHydrate().setA
			return article;
		}
	}






}
