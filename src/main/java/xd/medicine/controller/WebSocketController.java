package xd.medicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xd.medicine.entity.dto.AuthRequest;
import xd.medicine.entity.dto.InMessage;
import xd.medicine.entity.dto.OutMessage;
import xd.medicine.utils.GsonUtils;
import xd.medicine.websocket.SocketSessionRegistry;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * created by xdCao on 2017/12/25
 */
@RestController
public class WebSocketController {

    /**session操作类*/
    @Autowired
    private SocketSessionRegistry webAgentSessionRegistry;

    /**消息发送工具*/
    @Autowired
    private SimpMessagingTemplate template;


    /**
     * 同样的发送消息   只不过是ws版本  http请求不能访问
     * 根据用户key发送消息
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/msg/btgRequest")
    public void greeting2(String message) throws Exception {
        AuthRequest authRequest= GsonUtils.jsonToObject(message,AuthRequest.class);
        String sessionId=webAgentSessionRegistry.getSessionId(authRequest.getUserType()+","+authRequest.getUserId());
        template.convertAndSendToUser(sessionId,"/subject/info",
                new OutMessage(authRequest.toString()),createHeaders(sessionId));
    }
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}
