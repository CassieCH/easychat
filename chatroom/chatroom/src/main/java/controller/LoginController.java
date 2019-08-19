package controller;
import	java.util.HashMap;

import chatroom.utils.CommUtils;
import config.FreeMarkerListener;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private AccountService accountService = new AccountService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=utf8");
        PrintWriter out = resp.getWriter();
        if (CommUtils.strIsNull(userName) || CommUtils.strIsNull(password)) {
            // 登录失败,停留登录页面
            out.println("    <script>\n" +
                    "        alert(\"用户名或密码为空!\");\n" +
                    "        window.location.href = \"/index.html\";\n" +
                    "    </script>");
        }
        if (accountService.userLogin(userName,password)) {
            // 登录成功,跳转到聊天页面
            // 加载chat.ftl
            Template template = getTemplate(req,"/chat.ftl");
            Map<String, String> map = new HashMap<>();//前端的参数保存至此
            map.put("username",userName);
            try {
                template.process(map, out);//将用户名传到聊天页面
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }else {
            // 登录失败,停留在登录页面
            out.println("    <script>\n" +
                    "        alert(\"用户名或密码不正确!\");\n" +
                    "        window.location.href = \"/index.html\";\n" +
                    "    </script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    private Template getTemplate(HttpServletRequest req,String fileName) {//
        Configuration cfg = (Configuration)
                req.getServletContext().getAttribute(FreeMarkerListener.TEMPLATE_KEY);//获取全局的配置
        try {
            return cfg.getTemplate(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
