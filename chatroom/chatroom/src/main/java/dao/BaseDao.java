package dao;

import chatroom.utils.CommUtils;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//封装基础操作,数据源、获取连接、关闭资源

public class BaseDao {
    private static DataSource dataSource;
    static {
        Properties properties = CommUtils.
                loadProperties("datasource.properties");
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);//加载配置文件
        } catch (Exception e) {
            System.err.println("数据源加载失败");
        }
    }

    // 获取数据库连接
    protected Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("获取连接失败");
        }
        return null;
    }

    // 关闭更新资源 Statement Connection
    protected void closeResources(Connection connection,
                                  Statement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // 关闭查询类资源Statement ResultSet Connection
    protected void closeResources(Connection connection,
                                  Statement statement,
                                  ResultSet resultSet) {
        closeResources(connection,statement);
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    public static void main(String[] args) {
//            BaseDao dao = new BaseDao();
//            Connection connection = dao.getConnection();
//            String sql = "insert into user values(null,'q','123')";
//            try {
//                connection.prepareStatement(sql);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//    }

}
