package xd.medicine.websocket;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * created by xdCao on 2017/12/25
 */

public class SocketSessionRegistry {

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
        userSessionIds.remove(userName);
    }

}
