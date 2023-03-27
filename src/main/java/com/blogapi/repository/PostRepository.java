package com.blogapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.entity.Category;
import com.blogapi.entity.Post;
import com.blogapi.entity.User;

public interface PostRepository extends JpaRepository<Post,Integer>{
		List<Post> getByUser(User user); 
		List<Post> getByCategory(Category category);
		List<Post> getByPostTitleContaining(String key);
	  
}
