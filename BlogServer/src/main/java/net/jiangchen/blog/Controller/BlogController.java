package net.jiangchen.blog.Controller;

import net.jiangchen.blog.Model.BlogPO;
import net.jiangchen.blog.Model.UserPO;
import net.jiangchen.blog.Repository.BlogService;
import net.jiangchen.blog.RepositoryImpl.BlogServiceImpl;
import net.jiangchen.blog.dataHelper.DataHelper;
import net.jiangchen.blog.dataHelper.HibernateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@CrossOrigin
@Controller
@RequestMapping("/blog")
public class BlogController {
    private DataHelper<BlogPO> helper = new HibernateHelper<>(BlogPO.class);
    private DataHelper<UserPO> uhelper = new HibernateHelper<>(UserPO.class);
    private BlogService blogService = new BlogServiceImpl();

    @RequestMapping(
            value = "/read",
            method = RequestMethod.GET
    )
    public File read(String email, String bid) {
        return blogService.read(email, bid);
    }

    @RequestMapping(
            value = "/uploadImg",
            method = RequestMethod.POST
    )
    public String[][] uploadImg(String email, MultipartFile file[]) {
        int len = file.length;
        String[][] res = new String[len][2];
        blogService.uploadImg(email, file, res);
        return res;
    }

    @RequestMapping(
            value = "/uploadBlog",
            method = RequestMethod.POST
    )
    public boolean uploadBlog(String email, File file, BlogPO blogPO) {
        String bid = getBid(email);
        boolean b = blogService.create(email, bid, file);
        blogPO.setBid(bid);
        helper.save(blogPO);
        return b;
    }

    @RequestMapping(
            value = "/getInfo",
            method = RequestMethod.GET
    )
    public BlogPO getInfo(String bid) {
        BlogPO blogPO = helper.exactlyQuery("bid", bid);
        return blogPO;
    }

    public String getBid(String email) {
        UserPO userPO = uhelper.exactlyQuery("email", email);
        int uid = userPO.getUid();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String bid = uid + format.format(date);
        return bid;
    }
}
