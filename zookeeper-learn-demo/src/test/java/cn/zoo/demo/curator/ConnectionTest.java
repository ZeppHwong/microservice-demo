package cn.zoo.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ConnectionTest extends BaseTest {


    /**
     * 创建连接
     */
    @Test
    public void testConnection() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        curatorFramework.create().forPath("/test");
    }


    @Test
    public void testConnectionBuilder() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECT_HOST)
                .retryPolicy(RETRY_POLICY)
                .sessionTimeoutMs(1000)
                .connectionTimeoutMs(10000)
                .build();
        curatorFramework.start();
        curatorFramework.create().forPath("/test2");
    }

    @Test
    public void testCreateDefaultData() throws Exception {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().defaultData("default".getBytes(StandardCharsets.UTF_8));
        CuratorFramework client = builder.connectString(CONNECT_HOST).retryPolicy(RETRY_POLICY).build();
        client.start();
        client.create().forPath("/defaultDataTest");
    }

    /**
     * 名称空间
     */
    @Test
    public void testNamespace() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        CuratorFramework c2 = curatorFramework.usingNamespace("namespace");
        c2.create().forPath("/node1");
        c2.create().forPath("/node2", "value2".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void testWithMode() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/EPHEMERAL1");
        // 临时节点，会话结束就会删除，线程睡眠用于延长会话时间
        TimeUnit.SECONDS.sleep(30);
    }

    /**
     * 因为TTL默认是关闭的，需要打开（zoo.cfg中设置extendedTypesEnabled=true）
     *
     * @throws Exception
     */
    @Test
    public void testWithTTL() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        curatorFramework.create().withTtl(10000).withMode(CreateMode.PERSISTENT_WITH_TTL).forPath("/ttl1");
    }


    @Test
    public void testWithACL() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        List<ACL> aclList = new ArrayList<>();
        ACL acl = new ACL(ZooDefs.Perms.ALL, ZooDefs.Ids.ANYONE_ID_UNSAFE);
        aclList.add(acl);
        curatorFramework.create().withACL(aclList).forPath("/acl1");
    }


    @Test
    public void testGetData() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        byte[] bytes = curatorFramework.getData().forPath("/test");
        System.out.println("/test节点的值是:" + new String(bytes, StandardCharsets.UTF_8));
    }

    @Test
    public void testSetData() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        byte[] bytes = curatorFramework.getData().forPath("/test");
        System.out.println("/test节点的原始值是:" + new String(bytes, StandardCharsets.UTF_8));
        curatorFramework.setData().forPath("/test", "updated".getBytes(StandardCharsets.UTF_8));
        bytes = curatorFramework.getData().forPath("/test");
        System.out.println("/test节点的新值是:" + new String(bytes, StandardCharsets.UTF_8));
    }

    @Test
    public void testGetChildren() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        List<String> children = curatorFramework.getChildren().forPath("/namespace");
        children.forEach(System.out::println);
    }

    @Test
    public void testGetACL() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        List<ACL> acls = curatorFramework.getACL().forPath("/test");
        acls.forEach(acl -> System.out.println(acl.getId() + " " + acl.getPerms()));
    }

    @Test
    public void testDelete() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        curatorFramework.delete().forPath("/test");
    }

    @Test
    public void testDeleteChildren() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/namespace");
    }

    @Test
    public void testCheckExist() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        Stat stat = curatorFramework.checkExists().forPath("/namespace");
        System.out.println("stat:\n" + stat);
    }

    @Test
    public void testClientState() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        CuratorFrameworkState state = curatorFramework.getState();
        System.out.println("状态是" + state); // 状态是LATENT
        curatorFramework.start();
        state = curatorFramework.getState();
        System.out.println("状态是" + state); // 状态是STARTED
        curatorFramework.close();
        state = curatorFramework.getState();
        System.out.println("状态是" + state); // 状态是STOPPED
    }

    /**
     * 手动创建  /transaction2 /transaction3 命令 create /transaction2 create /transaction3
     */
    @Test
    public void testTransaction() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        CuratorOp createOp = curatorFramework.transactionOp().create().forPath("/transaction1");
        CuratorOp setDataOp = curatorFramework.transactionOp().setData().forPath("/transaction2", "transaction2".getBytes(StandardCharsets.UTF_8));
        CuratorOp deleteOp = curatorFramework.transactionOp().delete().forPath("/transaction3");

        List<CuratorTransactionResult> result = curatorFramework.transaction().forOperations(createOp, setDataOp, deleteOp);
        result.forEach(rt -> System.out.println(rt.getForPath() + "---" + rt.getType()));
    }

    @Test
    public void testListener() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
//        CuratorCache curatorCache = CuratorCache.builder(curatorFramework, "/ns1").build();
        CuratorCache curatorCache = CuratorCache.build(curatorFramework, "/ns1", CuratorCache.Options.SINGLE_NODE_CACHE);
        CuratorCacheListener curatorCacheListener = CuratorCacheListener.builder()
                .forInitialized(() -> {
                    log.info("forInitialized回调");
                    log.info("--------");
                })

                .forCreates(childData -> {
                    log.info("forCreates回调执行, path=[{}], data=[{}], stat=[{}]", childData.getPath(), Objects.isNull(childData.getData()) ? null : new String(childData.getData(), StandardCharsets.UTF_8), childData.getStat());
                    log.info("--------");
                })

                .forNodeCache(() -> {
                    log.info("forNodeCache回调");
                    log.info("--------");
                })

                .forChanges((oldNode, node) -> {
                    log.info("forChanges回调, oldNode.path=[{}], oldNode.data=[{}], oldNode.stat=[{}], node.path=[{}], node.data=[{}], node.stat=[{}]"
                            , oldNode.getPath(), Objects.isNull(oldNode.getData()) ? null : new String(oldNode.getData(), StandardCharsets.UTF_8), oldNode.getStat(),
                            node.getPath(), Objects.isNull(node.getData()) ? null : new String(node.getData(), StandardCharsets.UTF_8), node.getStat());
                    log.info("--------");
                })

                .forDeletes(childData -> {
                    log.info("forDeletes回调执行, path=[{}], data=[{}], stat=[{}]", childData.getPath(),
                            Objects.isNull(childData.getData()) ? null : new String(childData.getData(), StandardCharsets.UTF_8), childData.getStat());
                    log.info("--------");
                })

                .forAll((type, oldNode, node) -> {
                    log.info("forAll回调");
                    log.info("type=[{}]", type);
                    if (Objects.nonNull(oldNode)) {
                        log.info("oldNode.path=[{}], oldNode.data=[{}], oldNode.stat=[{}]", oldNode.getPath(),
                                Objects.isNull(oldNode.getData()) ? null : new String(oldNode.getData(), StandardCharsets.UTF_8), oldNode.getStat());
                    }
                    if (Objects.nonNull(node)) {
                        log.info("node.path=[{}], node.data=[{}], node.stat=[{}]", node.getPath(), Objects.isNull(node.getData()) ? null : new String(node.getData(), StandardCharsets.UTF_8), node.getStat());
                    }
                    log.info("--------");
                })

                .forCreatesAndChanges((oldNode, node) -> {
                    log.info("forCreatesAndChanges回调");
                    if (Objects.nonNull(oldNode)) {
                        log.info("oldNode.path=[{}], oldNode.data=[{}], oldNode.stat=[{}]", oldNode.getPath(), Objects.isNull(oldNode.getData()) ? null : new String(oldNode.getData(), StandardCharsets.UTF_8), oldNode.getStat());
                    }
                    if (Objects.nonNull(node)) {
                        log.info("node.path=[{}], node.data=[{}], node.stat=[{}]", node.getPath(), Objects.isNull(node.getData()) ? null : new String(node.getData(), StandardCharsets.UTF_8), node.getStat());
                    }
                    log.info("--------");
                })
                .build();
        // 获取监听器列表容器
        Listenable<CuratorCacheListener> listenable = curatorCache.listenable();
        // 将监听器放入容器中
        listenable.addListener(curatorCacheListener);
        // curatorCache必须启动
        curatorCache.start();
        // 延时，以保证连接不关闭
        TimeUnit.DAYS.sleep(10);
        curatorCache.close();
    }
}
