package net.jiangchen.blog.dataHelper;

import net.jiangchen.blog.Model.UserPO;
import net.jiangchen.blog.Repository.BlogService;
import net.jiangchen.blog.RepositoryImpl.BlogServiceImpl;

public class Test {
    DataHelper<UserPO> userDataHelper = new HibernateHelper<>(UserPO.class);

    public static void main(String[] args){
        BlogService blogService = new BlogServiceImpl();
        blogService.read("test",1);
    }

}

