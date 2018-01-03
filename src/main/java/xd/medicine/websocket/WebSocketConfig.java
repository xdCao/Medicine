package xd.medicine.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created by xdCao on 2017/12/25
 */
@EnableWebSocketMessageBroker
@Component
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{


    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/subject");
        registry.setApplicationDestinationPrefixes("/medicine");
    }

    @Bean
    public SocketSessionRegistry socketSessionRegistry(){
        return new SocketSessionRegistry();
    }

    @Bean
    public STOMPConnectEventListener stompConnectEventListener(){
        return new STOMPConnectEventListener();
    }



}
