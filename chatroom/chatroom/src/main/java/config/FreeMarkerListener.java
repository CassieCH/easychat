package config;
import	java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import freemarker.template.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class FreeMarkerListener implements ServletContextListener {//k-v形式读取k值
    public static final String TEMPLATE_KEY = "_template_";
    @Override
    public void contextInitialized(ServletContextEvent sce) {//用于生成模板以及处理模板与数据之间的关系并输出
        // 配置版本，实例化freemarker配置类
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        // 配置加载ftl的路径，给配置类配置路劲
        try {
            cfg.setDirectoryForTemplateLoading(
                    new File("E:\\chatroom\\src\\main\\webapp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 配置页面编码
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        sce.getServletContext().setAttribute(TEMPLATE_KEY,cfg);//将配置写入上下文中
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
