package xd.medicine.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * created by xdCao on 2018/1/5
 */

public class STOMPDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {

    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
        //logout get from browser
        String sessionId = sha.getSessionId();
        webAgentSessionRegistry.unregisterSessionId(sessionId);
    }
}
