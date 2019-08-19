package service;


import dao.AccountDao;
import entity.User;

public class AccountService {
    private AccountDao accountDao = new AccountDao();//service调用Dao层

    // 用户登陆
    public boolean userLogin(String userName,String password) {
        User user = accountDao.userLogin(userName,password);
        if (user == null) { //登录验证
            return false;
        }
        return true;
    }

    // 用户注册
    public boolean userRegister(String userName,String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        return accountDao.userRegister(user);//注册验证
    }
}
