package xd.medicine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import xd.medicine.cache.EmergMapCache;
import xd.medicine.entity.dto.AuthRequest;
import xd.medicine.service.DoctorService;
import xd.medicine.service.OthersService;
import xd.medicine.service.PatientService;
import xd.medicine.utils.GsonUtils;
import xd.medicine.websocket.SocketSessionRegistry;

import java.util.*;

/**
 * created by xdCao on 2017/12/25
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class WebSocketController {

    private EmergMapCache emergMapCache = EmergMapCache.getInstance();

    private static final Logger LOGGER= LoggerFactory.getLogger("------请求处理模块： ");

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
            LOGGER.info("获取到来自医生： "+authRequest.getUserId()+" 向病人："+authRequest.getPatientId()+"发起的紧急请求");
            checkAndPut(authRequest);
        }else if (authRequest.getUserType().equals(2)){
            LOGGER.info("获取到来自其他用户： "+authRequest.getUserId()+" 向病人："+authRequest.getPatientId()+"发起的紧急请求");
            checkAndPut(authRequest);
        }

    }

    @MessageMapping("/msg/riskRequest")
    public void riskRequest(String message){

    }

    private void checkAndPut(AuthRequest authRequest) {
        boolean containsKey = emergMapCache.containsKey(authRequest.getPatientId());
        if (containsKey){
            emergMapCache.get(authRequest.getPatientId()).add(authRequest.getUserType()+":"+authRequest.getUserId());
        }else {
            ArrayList<String> arrayList=new ArrayList<>();
            arrayList.add(authRequest.getUserType()+":"+authRequest.getUserId());
            emergMapCache.put(authRequest.getPatientId(),arrayList);
        }
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}
