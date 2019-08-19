package chatroom.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//封装基础的工具方法,如加载配置文件、json序列化等

public class CommUtils {
    private static final Gson gson = new GsonBuilder().create();//json序列化
    private CommUtils(){}//均是静态方法不需要产生对象

    /**
     * 根据指定的文件名加载配置文件
     * @param fileName 配置文件名
     * @return
     */
    public static Properties loadProperties(String fileName) {//Properties属性类 里面均是k=v的格式
        Properties properties = new Properties();
        // 获取当前配置文件夹下的文件输入流
        InputStream in = CommUtils.class.getClassLoader()
                .getResourceAsStream(fileName);//此方法获取此类加载器下所有同目录的文件（必须是资源文件夹）
        // load()加载配置文件中的所有内容
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;//已加载配置文件内容的对象
    }
//将对象变为字符串
    public static String object2Json(Object obj) {
        return gson.toJson(obj);
    }
//字符串变为对象
    public static Object json2Object(String jsonStr,Class objClass) {
        return gson.fromJson(jsonStr,objClass);
    }

    public static boolean strIsNull(String str) {
        return str == null || str.equals("");
    }//判断字符串为空？预防空指针异常，将str==null放在前面,预防前端页面没有处理
}
