package dao;


import entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

//关于用户模块的dao层
public class AccountDao extends BaseDao{//直接有数据源、连接

    // 用户登录 select
    public User userLogin(String userName, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM user WHERE username = ? AND " +
                    " password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,userName);//替换？的位置
            statement.setString(2, DigestUtils.md5Hex(password));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUserInfo(resultSet);//将resultset变为类的对象，不涉及关闭等问题
            }
        }catch (Exception e) {
            System.err.println("查询用户信息出错");
            e.printStackTrace();
        }finally {
            closeResources(connection,statement,resultSet);
        }
        return user;
    }

    // 用户注册 insert
    public boolean userRegister(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isSuccess = false;//默认不成功，若成功了
        try {
            connection = getConnection();
            String sql = "INSERT INTO user(username, password)" +
                    " VALUES(?,?) ";
            statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//返回主键受影响的行数
            statement.setString(1,userName);
            statement.setString(2,DigestUtils.md5Hex(password));//密码加密
            isSuccess = (statement.executeUpdate() == 1);
        }catch (Exception e) {
            System.err.println("用户注册失败");
            e.printStackTrace();
        }finally {
            closeResources(connection,statement);
        }
        return isSuccess;
    }

    // 将数据表信息封装到创建好的User类中--以防资源关闭后不能调用
    public User getUserInfo(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

    public static void main(String[] args) {
        AccountDao dao = new AccountDao();
        //Connection connection = dao.getConnection();
        User tom = dao.userLogin("Tom", DigestUtils.md5Hex("123"));
        System.out.println(tom);
    }

}
