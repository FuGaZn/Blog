package net.jiangchen.dataHelper;

import net.jiangchen.Model.UserPO;
import net.jiangchen.util.ResultMessage;

public class Test {
    DataHelper<UserPO> userDataHelper = new HibernateHelper<>(UserPO.class);

    public static void main(String[] args){
        UserPO userPO = new UserPO(0, "jiang-chen",0,"jiaxing_f@163.com","jiang-chen.net");
        Test test = new Test();
        ResultMessage resultMessage = test.userDataHelper.save(userPO);
        System.out.println(resultMessage);
    }

}

