package xd.medicine.websocket;

import com.sun.tools.javac.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**
 * created by xdCao on 2017/12/25
 */

public class STOMPConnectEventListener implements ApplicationListener<SessionConnectEvent>{

    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;


    @Override
    public void onApplicationEvent(SessionConnectEvent sessionConnectEvent) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());
        //login get from browser
        String userType = sha.getNativeHeader("userType").get(0);
        String userId=sha.getNativeHeader("userId").get(0);
        String userKey=userType+":"+userId;
        String sessionId = sha.getSessionId();
        System.out.println("agentId:    "+userKey+" ,   sessionId:      "+sessionId);
        webAgentSessionRegistry.registerSessionId(userKey,sessionId);
    }
}
