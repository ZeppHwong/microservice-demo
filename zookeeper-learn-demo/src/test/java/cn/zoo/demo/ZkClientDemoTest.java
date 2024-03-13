package cn.zoo.demo;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ZkClientDemoTest {
    @Test
    public void testCZkClient() throws InterruptedException {
        ZkClient zkClient = new ZkClient(
                "localhost:2181,localhost:2182,localhost:2183",
                20000
        );

        // 创建节点
        zkClient.createPersistent("/abc", "hello");
        zkClient.createEphemeral("/xyz", "world");
        zkClient.create("/opq", "world", CreateMode.EPHEMERAL_SEQUENTIAL);

        String data = zkClient.readData("/abc");
        System.out.println(data);

        // 监听状态变化
        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
                System.out.println("state:" + keeperState);
            }

            @Override
            public void handleNewSession() throws Exception {
                System.out.println("new session");
            }

            @Override
            public void handleSessionEstablishmentError(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });

        // 监听子节点发生变化
        zkClient.subscribeChildChanges("/", new IZkChildListener() {
            @Override
            public void handleChildChange(String path, List<String> list) throws Exception {
                System.out.println("watch path:" + path);
                // 输出所有子节点
                list.forEach(str -> {
                    System.out.println(str);
                });
            }
        });

        Thread.sleep(100000);
    }
}
