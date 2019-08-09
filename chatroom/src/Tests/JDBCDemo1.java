

/**import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;



//JDBC操作数据库
public class JDBCDemo1 {
    @Test
    public void testQuery() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE id = ?";
            statement = connection.prepareStatement(sql);
            resultSet=statement.executeQuery(sql);
            // statement.setInt(1, 8);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("id为" + id + ",用户名为:" + userName +
                        "，密码为" + password);
            }
        } catch (SQLException e) {
        }
    }
    /** public void testInsert() {
     Connection connection = null;
     PreparedStatement statement = null;
     try {
     connection = JDBCUtils.getConnection();
     String sql = "INSERT INTO user(username, password) VALUES(?,?)";
     statement = connection.prepareStatement(sql);
     statement.setString(1,"java7");//插入用户名
     statement.setString(2, DigestUtils.md5Hex("java7"));//插入密码，采用MD5的加密方式
     int yingXiang = statement.executeUpdate();//受影响的行数
     Assert.assertEquals(1,yingXiang);
     }catch (SQLException e) {

     }finally {
     JDBCUtils.closeResources(connection,statement);
     }
     }*/




