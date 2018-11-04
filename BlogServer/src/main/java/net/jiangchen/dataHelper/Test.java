package net.jiangchen.dataHelper;

import net.jiangchen.Model.User;
import net.jiangchen.util.ResultMessage;

import javax.xml.crypto.Data;

public class Test {
    DataHelper<User> userDataHelper = new HibernateHelper<>(User.class);

    public static void main(String[] args){
        User user = new User(0, "jiang-chen",0,"jiaxing_f@163.com","jiang-chen.net");
        Test test = new Test();
        ResultMessage resultMessage = test.userDataHelper.save(user);
        System.out.println(resultMessage);
    }

}

