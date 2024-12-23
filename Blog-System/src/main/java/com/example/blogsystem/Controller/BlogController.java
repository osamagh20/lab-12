package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get")
    public ResponseEntity getblogs(){
        List<Blog> blogs = blogService.getBlogList();
        return ResponseEntity.status(200).body(blogs);
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid Blog blog){
        blogService.addBlog(myUser.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog added"));
    }

    @PutMapping("/update/{blog_id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blog_id, @RequestBody @Valid Blog blog){
        blogService.updateBlog(myUser.getId(),blog_id,blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blog_id){
        blogService.deleteBlog(myUser.getId(),blog_id);
        return ResponseEntity.status(200).body(new ApiResponse("Blog deleted"));
    }

    @GetMapping("/search-title/{title}")
    public ResponseEntity searchBlog(@AuthenticationPrincipal MyUser myUser,@PathVariable String title){
        Blog blogByTitle = blogService.getBlogByTitle(myUser.getId(),title);
        return ResponseEntity.status(200).body(blogByTitle);
    }

    @GetMapping("/search-id/{blog_id}")
    public ResponseEntity searchByCategory(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blog_id){
        Blog blogById = blogService.blogById(myUser.getId(),blog_id);
        return ResponseEntity.status(200).body(blogById);

    }

    @GetMapping("/get-my-blogs")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal MyUser myUser){
        Set<Blog> blogSet = blogService.getMyBlogs(myUser.getId());
        return ResponseEntity.status(200).body(blogSet);
    }
}
