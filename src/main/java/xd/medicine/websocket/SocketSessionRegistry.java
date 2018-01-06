package xd.medicine.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * created by xdCao on 2017/12/25
 */

public class SocketSessionRegistry {

    private static final Logger LOGGER= LoggerFactory.getLogger(SocketSessionRegistry.class);

    private final ConcurrentHashMap<String,String> userSessionIds=new ConcurrentHashMap<>();

    public SocketSessionRegistry() {
    }

    public String getSessionId(String user){
        String sessionId=this.userSessionIds.get(user);
        return sessionId;
    }

    public ConcurrentHashMap<String,String> getUserSessionIds() {
        return userSessionIds;
    }

    public void registerSessionId(String user,String sessionId){
        userSessionIds.put(user,sessionId);
    }

    public void unregisterSessionId(String userName, String sessionId) {
        userSessionIds.remove(userName,sessionId);
    }

    public void unregisterSessionId(String sessionId){
        userSessionIds.forEach((key,value)->{
            if (value.equals(sessionId)){
                userSessionIds.remove(key,value);
                LOGGER.info("user key: "+key+" , sessionId: "+sessionId+" ,已被移除");
            }
        });
    }

}
