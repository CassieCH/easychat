package controller;


import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//加了此注解的类会被Tomcat加载成一个servlet类
@WebServlet(urlPatterns = "/doRegister") //访问地址：表单路径
public class AccountController extends HttpServlet {
    private AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=utf8");//解决中文乱码
        PrintWriter writer = resp.getWriter();//获取页面的输出流
        if (accountService.userRegister(userName,password)) {
            // 用户注册成功
            // 弹框提示，跳转登陆界面
            writer.println("<script>\n" +
                    "    alert(\"注册成功\");\n" +
                    "    window.location.href = \"/index.html\";\n" +
                    "</script>");
        }else {
            // 弹框提示失败，停留在原页面
            writer.println("<script>\n" +
                    "    alert(\"注册失败\");\n" +
                    "    window.location.href = \"/registration.html\";\n" +
                    "</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
