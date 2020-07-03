import bean.Msg;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Rice
 * @create 2020/06/24 15:56
 */
@ServerEndpoint("/ws/{sid}")
public class WSServer {

    private Session session ;
    private static ConcurrentHashMap<String,Session> ss = new ConcurrentHashMap<String,Session>();
    private int oln ;

    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid){
        oln++;
        this.session = session;
        ss.put(sid,session);
    }

    @OnMessage
    public void onMessage(String message ,Session session,@PathParam("sid") String sid ) throws IOException {
        Msg msg = Msg.getInstance(message);
        msg.setUser(sid);
        if(msg.getTid()==1){
            privateChat(msg);
            return ;
        }

    }

    @OnClose
    public void onClose(@PathParam("sid") String sid){
        oln--;
        ss.remove(sid);
    }

    @OnError
    public void onError(Session session ,Throwable error){
        System.out.println(error);
    }


    private void privateChat(Msg msg) throws IOException {
        if (ss.containsKey(msg.getTsid())){
            Session ts = ss.get(msg.getTsid());
            ts.getBasicRemote().sendText(Msg.forwordMSg(msg));
        }else{
            this.session.getBasicRemote().sendText("对方不在线");
        }
    }

}
