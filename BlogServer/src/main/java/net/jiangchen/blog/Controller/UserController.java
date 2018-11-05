package net.jiangchen.blog.Controller;

import com.sun.xml.internal.ws.api.pipe.ContentType;
import net.jiangchen.blog.Model.UserPO;
import net.jiangchen.blog.dataHelper.DataHelper;
import net.jiangchen.blog.dataHelper.HibernateHelper;
import net.jiangchen.blog.util.ResultMessage;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private DataHelper<UserPO> helper = new HibernateHelper<>(UserPO.class);

    @RequestMapping("/get")
    public UserPO get(){
        return helper.exactlyQuery("uid",1);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    public boolean add(@RequestBody UserPO userPO){
        if(helper.save(userPO) == ResultMessage.SUCCESS)
            return true;
        return false;
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET
    )
    public boolean login(@RequestParam(value = "email") String email,@RequestParam(value = "password") String password) {
        UserPO userPO = helper.exactlyQuery("email", email);
        if(userPO == null)
            return false;
        if (userPO.getPasswd().equals(password.trim())) {
            return true;
        }

        return false;
    }

}
