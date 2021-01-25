package alian.config;

import alian.domain.Student;
import alian.domain.Teacher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@ServerEndpoint("/websocket/{uid}")
//@Component
public class WebSocketServer {

    static Log log= LogFactory.getLog(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
//    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();


    private static Map<String, WebSocketServer> webSocketServerMap = new HashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String uid="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("uid") String uid) {
        this.session = session;
//        webSocketSet.add(this);     //加入set中
        /*
        *
        * 将用户标识和用户加入Map集合
        *
        * */
        if(webSocketServerMap.size()==0||!webSocketServerMap.containsKey("uid"))
        {
            webSocketServerMap.put(uid,this);
        }
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听:"+uid+",当前在线人数为" + getOnlineCount());
        this.uid=uid;
        try {
            sendMessage("success");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {

//        webSocketSet.remove(this);  //从set中删除
        webSocketServerMap.remove(this.uid);
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+uid+"的信息:"+message);
        //群发消息
//        for (WebSocketServer item : webSocketServerMap) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        //第三种：推荐，尤其是容量大时
        System.out.println("\n通过Map.entrySet遍历key和value");
        for(Map.Entry<String, WebSocketServer> entry: webSocketServerMap.entrySet())
        {
            try {
                entry.getValue().sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    /*
    *
    *
    * 自定义群发消息
    *
    *
    * */
    public static void sendInfosToTeacher(String message,List<Teacher> teachers)
    {
        /**
         *
         *学生给老师发送消息
         *
         */

        for(Teacher teacher: teachers)
        {
            String tcode=teacher.getTcode();
            if(webSocketServerMap.containsKey(tcode))
            {
                try {
                    webSocketServerMap.get(tcode).sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void sendInfosToStudent(String message,List <Student> students)
    {
        /**
         *
         *老师给学生发送信息
         *
         */

        for(Student student: students)
        {
            String tcode=student.getScode();
            if(webSocketServerMap.containsKey(tcode))
            {
                try {
                    webSocketServerMap.get(tcode).sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
