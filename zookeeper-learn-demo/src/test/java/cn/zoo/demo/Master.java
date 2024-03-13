package cn.zoo.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Master implements Watcher {
    private ZooKeeper zk;
    private String host;
    private String serverId;
    private boolean isLeader;
    private final AsyncCallback.StringCallback masterCreateCallback = (i, s, o, s1) -> {
        switch (KeeperException.Code.get(i)) {
            case OK:
                isLeader = true;
                break;
            case CONNECTIONLOSS:
                checkMasterAsync();
                return;
            default:
                isLeader = false;

        }
        System.out.println("I'm " + (isLeader ? "" : "not") + "the leader");
    };

    private final AsyncCallback.DataCallback masterCheckCallBack = (i, s, o, bytes, stat) -> {
        switch (KeeperException.Code.get(i)) {
            case CONNECTIONLOSS:
                checkMasterAsync();
                break;
            case NONODE:
                runForMasterAsync();
                break;
        }
    };

    public Master(String host) {
        this.host = host;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void startZK() throws IOException {
        zk = new ZooKeeper(host, 5000, this);
    }

    public void stopZK() throws InterruptedException {
        zk.close();
    }

    public void runForMaster() throws InterruptedException, KeeperException {
        serverId = Integer.toHexString(new Random().nextInt());
        isLeader = false;
        while (true) {
            try {
                zk.create("/master", serverId.getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                isLeader = true;
                break;
            } catch (KeeperException.NoNodeException e) {
                System.out.println("WatcherDemo.runForMaster" + e);
                break;
            } catch (KeeperException.ConnectionLossException | KeeperException.NodeExistsException e) {
                System.out.println("WatcherDemo.runForMaster" + e);
            }
            if (checkMaster()) break;
        }
    }

    public void runForMasterAsync() {
        serverId = Integer.toHexString(new Random().nextInt());
        isLeader = false;
        zk.create("/master", serverId.getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,
                masterCreateCallback, null);
        isLeader = true;
    }

    public void checkMasterAsync() {
        zk.getData("/master", false, masterCheckCallBack, null);
    }

    public boolean checkMaster() throws InterruptedException, KeeperException {
        isLeader = false;
        while (true) {
            try {
                Stat stat = new Stat();
                byte data[] = zk.getData("/master", false, stat);
                isLeader = new String(data).equals(serverId);
                return true;
            } catch (KeeperException.NoNodeException e) {
                return false;
            } catch (KeeperException.ConnectionLossException e) {

            }
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("WatcherDemo.process::" + watchedEvent);
    }

    private final AsyncCallback.StringCallback createParentCallback = (rc, path, ctx, name) -> {
        switch (KeeperException.Code.get(rc)) {
            case CONNECTIONLOSS:
                createParent(path, (byte[]) ctx);
                break;
            case OK:
                System.out.println("Parent created");
                break;
            case NODEEXISTS:
                System.out.println("Parent already registered: " + path);
                break;
            default:
                System.out.println("Something went wrong: " + KeeperException.create(KeeperException.Code.get(rc), path));
        }
    };

    private void createParent(String path, byte[] data) {
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, createParentCallback, data);
    }

    public void bootstrap() {
        createParent("/workers", new byte[0]);
        createParent("/assign", new byte[0]);
        createParent("/tasks", new byte[0]);
        createParent("/status", new byte[0]);
    }
}
