package xd.medicine.cache;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created by xdCao on 2018/1/3
 */

public class UnknownMapCache {

    private static final int DEFAULT_CACHES=1024;

    private static final UnknownMapCache instance=new UnknownMapCache();

    private ConcurrentHashMap<Integer,List<String>> cachePool;

    public static UnknownMapCache getInstance(){
        return instance;
    }

    public UnknownMapCache() {
        this(DEFAULT_CACHES);
    }

    public UnknownMapCache(int cacheCount) {
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
