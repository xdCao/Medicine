package xd.medicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import xd.medicine.cache.MapCache;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Others;
import xd.medicine.entity.dto.AuthRequest;
import xd.medicine.entity.dto.InMessage;
import xd.medicine.entity.dto.OutMessage;
import xd.medicine.service.DoctorService;
import xd.medicine.service.OthersService;
import xd.medicine.service.PatientService;
import xd.medicine.utils.GsonUtils;
import xd.medicine.websocket.SocketSessionRegistry;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * created by xdCao on 2017/12/25
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class WebSocketController {

    private MapCache mapCache=MapCache.getInstance();

    /**session操作类*/
    @Autowired
    private SocketSessionRegistry webAgentSessionRegistry;

    /**消息发送工具*/
    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private OthersService othersService;

    /**
     * 同样的发送消息   只不过是ws版本  http请求不能访问
     * 根据用户key发送消息
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/msg/btgRequest")
    public void btgRequest(String message) throws Exception {
        AuthRequest authRequest= GsonUtils.jsonToObject(message,AuthRequest.class);
        //这里是用户发起授权请求的地方，在此处将用户信息放入MapCache，在定时任务中会取出缓存进行授权
        if (authRequest.getUserType().equals(1)){
            checkAndPut(authRequest);
        }else if (authRequest.getUserType().equals(2)){
            checkAndPut(authRequest);
        }

    }

    private void checkAndPut(AuthRequest authRequest) {
        boolean containsKey = mapCache.containsKey(authRequest.getPatientId());
        if (containsKey){
            mapCache.get(authRequest.getPatientId()).add(authRequest.getUserType()+":"+authRequest.getUserId());
        }else {
            ArrayList<String> arrayList=new ArrayList<>();
            arrayList.add(authRequest.getUserType()+":"+authRequest.getUserId());
            mapCache.put(authRequest.getPatientId(),arrayList);
        }
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}
