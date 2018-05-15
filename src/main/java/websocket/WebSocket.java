package websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class WebSocket extends TextWebSocketHandler {

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebSocketSession> webSocketSet = new CopyOnWriteArraySet<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        webSocketSet.forEach(webSocketSession -> {
            TextMessage returnMessage = new TextMessage(message.getPayload() + " received at server");
            try {
                webSocketSession.sendMessage(returnMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        addOnlineCount();
        webSocketSet.add(session);

        System.out.println("afterConnectionEstablished " + getOnlineCount());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        subOnlineCount();
        webSocketSet.remove(session);

        System.out.println("afterConnectionClosed " + getOnlineCount());
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
