package websocket;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import pojo.Message;
import pojo.ModuleId;
import pojo.User;
import util.StringUtil;
import util.WebSocketHttpHeaderUtil;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocket extends TextWebSocketHandler {

    private static int onlineCount = 0;

    private static Map<String, WebSocketSession> webSocketHashMap = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

//        webSocketHashMap.forEach((user, webSocketSession) -> {
//            TextMessage returnMessage = new TextMessage(message.getPayload() + " received at server");
//            try {
//                webSocketSession.sendMessage(returnMessage);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
        parseMessage(session, message.getPayload());

    }

    /**
     * parse Message
     *
     * @param session
     * @param payload
     */
    private void parseMessage(WebSocketSession session, String payload) {

        try {
            Message message = JSON.parseObject(payload, Message.class);

            if (ModuleId.WEBSOCKET_SERVER_ID.equals(message.getDestinationId())) {
                session.sendMessage(new TextMessage("login success"));
            } else {
                WebSocketSession webSocketSession = webSocketHashMap.get(message.getDestinationId());
                if (webSocketSession != null) {
                    webSocketSession.sendMessage(new TextMessage(JSON.toJSONString(message)));
                } else {
                    System.out.println("webSocketSession is null, getDestinationId : " + message.getDestinationId());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        User user = WebSocketHttpHeaderUtil.parseUserByHeaders(session.getHandshakeHeaders());
        String id = user.getId();
        if (!StringUtil.isEmpty(id)) {

            addOnlineCount();
            webSocketHashMap.put(id, session);

            System.out.println("afterConnectionEstablished " + getOnlineCount());
        } else {
            System.out.println("user id is null......");
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        User user = WebSocketHttpHeaderUtil.parseUserByHeaders(session.getHandshakeHeaders());
        String id = user.getId();

        if (!StringUtil.isEmpty(id)) {
            subOnlineCount();
            webSocketHashMap.remove(id);

            System.out.println("afterConnectionClosed " + getOnlineCount());
        } else {
            System.out.println("user id is null......");
        }

    }

    public static int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        onlineCount--;
    }

}
