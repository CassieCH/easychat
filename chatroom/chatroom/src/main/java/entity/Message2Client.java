package entity;



import java.util.Map;

//后端发送给前端的信息实体


public class Message2Client {
    // 聊天内容
    private String content;
    // 服务端登录的所有用户列表--前端页面
    private Map<String, String> names;//<session ID,用户名>

    public void setContent(String msg) {
        this.content = msg;
    }
    public void setContent(String userName,String msg) {
        this.content = userName + ":" + msg;
    }

    public void setNames(Map<String, String> names) {
        this.names = names;
    }
}
