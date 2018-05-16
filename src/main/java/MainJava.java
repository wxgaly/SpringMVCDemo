import com.alibaba.fastjson.JSON;
import okhttp3.*;
import okio.ByteString;
import pojo.Message;
import pojo.ModuleId;
import util.WebSocketHttpHeaderUtil;

import java.util.UUID;

/**
 * PACKAGE_NAME.MainJava
 *
 * @author Created by WXG on 2018/5/15 015 11:06.
 * @version V1.0
 */

public class MainJava {

    public static void main(String[] args) {
        testWebSocket();
    }

    private static void testWebSocket() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url("ws://127.0.0.1:8080/websocket")
                .addHeader(WebSocketHttpHeaderUtil.KEY_USERNAME, UUID.randomUUID().toString())
                .addHeader(WebSocketHttpHeaderUtil.KEY_ID, ModuleId.CLIENT2)
                .build();
        okHttpClient.newWebSocket(request, new EchoWebSocketListener());
    }

    public static class EchoWebSocketListener extends WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {

            webSocket.send(JSON.toJSONString(new Message(ModuleId.CLIENT2, ModuleId.WEBSOCKET_SERVER_ID, "login")));
            webSocket.send(JSON.toJSONString(new Message(ModuleId.CLIENT2, ModuleId.CLIENT1, "你好，我是一号")));
//            webSocket.send("welcome");
//            webSocket.send(ByteString.decodeHex("adef"));
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            output("onMessage: " + text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            output("onMessage byteString: " + bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            output("onClosing: " + code + "/" + reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            output("onClosed: " + code + "/" + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            output("onFailure: " + t.getMessage());
        }

    }

    private static void output(String s) {
        System.out.println(s);
    }

}
