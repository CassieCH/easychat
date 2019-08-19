package chatroom.utils;


import entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class CommUtilsTest {

    @Test
    public void loadProperties() {
        String fileName = "datasource.properties";
        Properties properties = CommUtils.loadProperties(fileName);
        System.out.println(properties);
        String url = properties.getProperty("url");//测试若成功，传入url正确
        Assert.assertNotNull(url);//单元测试通过
    }

    @Test//测试json序列化
    public void gsonTest1() {
        User user = new User();
        user.setId(10);
        user.setUserName("test");
        user.setPassword("123");
        String jsonStr = CommUtils.object2Json(user);
        System.out.println(jsonStr);//测试通过
    }

    @Test
    public void gsonTest2() {
        String jsonStr = "{\"id\":10,\"userName\":\"test\",\"password\":\"123\"}";
        User user = (User) CommUtils.json2Object(jsonStr,User.class);
        System.out.println(user);//测试通过
    }
}