package cn.zoo.demo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class ZookeeperDemoTest {
    private static final String host = "localhost:2181,localhost:2182,localhost:2183";

    @Test
    public void testZookeeper() throws KeeperException, InterruptedException, IOException {

        // 创建zk连接
        ZooKeeper zk = new ZooKeeper(
                host,
                20000,
                null
        );
        Stat abc = zk.exists("/abc", false);
        if (abc != null) {
            zk.delete("/abc", -1);
        }
        // 创建节点
        zk.create("/abc", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 获取节点数据
        // getData(String path, boolean watch, Stat stat);
        Stat stat = new Stat();
        byte[] data = zk.getData("/abc", false, stat);
        System.out.println(new String(data));       // 数据内容 123
        System.out.println(stat.getDataLength());   // 节点状态（数据长度） 3

        // 对/abc进行watch
        zk.getData("/abc",
                watchedEvent -> {
                    System.out.println("path:" + watchedEvent.getPath());
                    System.out.println("KeeperState:" + watchedEvent.getState());
                    System.out.println("EventType:" + watchedEvent.getType());
                },
                null);

        // 设置节点数据
        // setData(String path, byte[] data, int version)
        // 指定version为-1，表示不关心版本
        zk.setData("/abc", "456".getBytes(), -1);

        // 设置两次，第二次不会触发通知
        zk.setData("/abc", "789".getBytes(), -1);
        // 阻塞，以等待通知
        Thread.sleep(1000);
        zk.close();
    }

    @Test
    public void testWatcher() throws Exception {
        Master watcher = new Master("localhost:2181");
        watcher.startZK();
        // wait for a bit
        watcher.runForMaster();
        if (watcher.isLeader()) {
            System.out.println("I'm leader");
            Thread.sleep(10000);
        } else {
            System.out.println("Someone else is leader");
        }
        watcher.stopZK();
    }
}
