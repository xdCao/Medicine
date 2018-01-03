package xd.medicine.cache;
/*
    created by xdCao on 2017/10/10
*/
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class EmergMapCache {

    private static final int DEFAULT_CACHES=1024;

    private static final EmergMapCache instance=new EmergMapCache();

    private ConcurrentHashMap<Integer,List<String>> cachePool;

    public static EmergMapCache getInstance(){
        return instance;
    }

    public EmergMapCache() {
        this(DEFAULT_CACHES);
    }

    public EmergMapCache(int cacheCount) {
        cachePool = new ConcurrentHashMap<>(cacheCount);
    }


    public void put(Integer integer,List<String> list){
        cachePool.put(integer,list);
    }

    public List<String> get(Integer integer){
        return cachePool.get(integer);
    }

    public boolean containsKey(Integer integer){
        return cachePool.containsKey(integer);
    }

    public Integer size(){
        return cachePool.size();
    }

    public void remove(Integer key){
        cachePool.remove(key);
    }

}
