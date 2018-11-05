package net.jiangchen.blog.dataHelper;

import net.jiangchen.blog.Model.UserPO;

public class Test {
    DataHelper<UserPO> userDataHelper = new HibernateHelper<>(UserPO.class);

    public static void main(String[] args){
        //UserPO userPO = new UserPO(0, "jiang-chen",0,"jiaxing_f@163.com","jiang-chen.net");
        Test test = new Test();
      //  System.out.println(test.userDataHelper.exactlyQuery("uid",1));
       // UserController userController = new UserController();
      //  System.out.println(userController.get().getUid());
      //  System.out.println(userPO1);
    }

}

