package com.atjl.kafka.core;

import com.atjl.util.collection.CollectionUtil;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ZookeeperOperator
 * @since 1.0
 */
public class ZookeeperOperator implements Watcher {
    private static Logger LOG = LoggerFactory.getLogger(ZookeeperOperator.class);
    private ZooKeeper zooKeeper = null;

    public static final String PATH_SEPERATOR = "/";
    public ZookeeperOperator(String zookeeperHost) throws IOException {
        zooKeeper = new ZooKeeper(zookeeperHost, 5000, ZookeeperOperator.this);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
    }

    public boolean setTopicGroupOffset(String topic, String group,
                                       String partition, String data) {
        StringBuilder sb = new StringBuilder().append("/consumers/").append(group)
                .append("/offsets/").append(topic).append("/").append(partition);
        return setData(sb.toString(), data);
    }

    private boolean setData(String path, String data) {
        try {
            zooKeeper.setData(path, data.getBytes(), -1);
            if(LOG.isDebugEnabled())
                LOG.debug("zk set data,set success path {},data {}",path,data);
        } catch (KeeperException e) {
            LOG.error("Set error,KeeperException:" + path + " data:" + data, e);
            return false;
        } catch (InterruptedException e) {
            LOG.error("Set error,InterruptedException:" + path + " data:" + data, e);
            return false;
        }
        return true;
    }


    public List<String> getChildrenList(String path) {
        try {
            return zooKeeper.getChildren(path, false, null);
        } catch (KeeperException e) {
            LOG.error("Read error,KeeperException:" + path, e);
            return null;
        } catch (InterruptedException e) {
            LOG.error("Read error,InterruptedException:" + path, e);
            return null;
        }
    }

    public String getData(String path){
        try {
            return new String(zooKeeper.getData(path, false, null));
        } catch (KeeperException e) {
            LOG.error("Read error,KeeperException:" + path, e);
            return "";
        } catch (InterruptedException e) {
            LOG.error("Read error,InterruptedException:" + path, e);
            return "";
        }
    }

    public Map<String,String> getAllChildData(String path){
        List<String> childs = getChildrenList(path);
        if(CollectionUtil.isEmpty(childs)){
            return new HashMap<>();
        }
        Map<String,String> res = new HashMap<>();
        for(String child:childs){
            String childPath = path+ PATH_SEPERATOR +child;
            String data = getData(childPath);
            res.put(child,data);
        }
        return res;
    }
    

    public void close() {
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            LOG.error("Failed to close zookeeper:", e);
        }
    }
    

}
