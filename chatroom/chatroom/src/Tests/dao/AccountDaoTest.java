package dao;

import entity.User;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDaoTest {
    private AccountDao accountDao = new AccountDao();
    @Test
    public void userLogin() {
        User user = accountDao.userLogin("test1","123");
        System.out.println(user);
        Assert.assertNotNull(user);//测试通过
    }

    @Test
    public void userRegister() {
        User user = new User();
        user.setUserName("test1");
        user.setPassword("123");
        boolean isSuccess = accountDao.userRegister(user);
        Assert.assertEquals(true,isSuccess);
    }//测试通过且相同用户名只能注册一次
}