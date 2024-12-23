package com.example.blogsystem.Service;
import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Repository.AuthRepository;
import com.example.blogsystem.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getBlogList(){
        return blogRepository.findAll();
    }

    public Set<Blog> getMyBlogs(Integer user_id){
        MyUser user = authRepository.findMyUserById(user_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        return user.getBlogs();
    }

    public void addBlog(Integer user_id, Blog blog){
        MyUser user = authRepository.findMyUserById(user_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer user_id,Integer blog_id,Blog blog){
        MyUser user = authRepository.findMyUserById(user_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        Blog oldBlog = blogRepository.findBlogById(blog_id);
        if(oldBlog==null){
            throw new ApiException("blog not found");
        }

        if (user.getId().equals(blog_id)){
            oldBlog.setTitle(blog.getTitle());
            oldBlog.setBody(blog.getBody());
            oldBlog.setCategory(blog.getCategory());
            blogRepository.save(oldBlog);
        }
    }

    public void deleteBlog(Integer user_id,Integer blog_id){
        MyUser user = authRepository.findMyUserById(user_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        Blog oldBlog = blogRepository.findBlogById(blog_id);
        if(oldBlog==null){
            throw new ApiException("blog not found");
        }
        if (user.getId().equals(blog_id)){
            blogRepository.delete(oldBlog);
        }

    }

    public Blog blogById(Integer user_id,Integer blog_id){
        MyUser user = authRepository.findMyUserById(user_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        Blog blogById = blogRepository.findBlogById(blog_id);
        if(blogById ==null){
            throw new ApiException("blog not found");
        }

       return blogById;

    }

    public Blog getBlogByTitle(Integer user_id,String title){
        MyUser user = authRepository.findMyUserById(user_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        Blog blogByTitle = blogRepository.findBlogByTitle(title);
        if(blogByTitle==null){
            throw new ApiException("blog not found");
        }
        return blogByTitle;
    }
}
