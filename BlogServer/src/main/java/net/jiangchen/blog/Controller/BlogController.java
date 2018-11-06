package net.jiangchen.blog.Controller;

import net.jiangchen.blog.Repository.BlogService;
import net.jiangchen.blog.RepositoryImpl.BlogServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;

@CrossOrigin
@Controller
@RequestMapping("/blog")
public class BlogController {


    @RequestMapping(
            value = "/read",
            method = RequestMethod.GET
    )
    public File read(String email, int fid){
        BlogService blogService = new BlogServiceImpl();
        return blogService.read(email,fid);
    }

}
