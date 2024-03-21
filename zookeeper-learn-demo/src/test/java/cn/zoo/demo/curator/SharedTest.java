package cn.zoo.demo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.framework.recipes.atomic.PromotedToLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryForever;
import org.apache.curator.retry.RetryOneTime;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedTest extends BaseTest {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    @Test
    public void testShareCount() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        SharedCount sharedCount = new SharedCount(curatorFramework, "/ShareCount", 0);
        sharedCount.start();
        sharedCount.addListener(new SharedCountListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

            }

            @Override
            public void countHasChanged(SharedCountReader sharedCountReader, int newCount) {
                log.info("countHasChanged callback");
                log.info("newCount={}", newCount);
            }
        }, EXECUTOR_SERVICE);
        sharedCount.setCount(1);
        TimeUnit.DAYS.sleep(1);
        sharedCount.close();
    }

    @Test
    public void testDistributedAtomicLong() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, new RetryForever(1000));
        curatorFramework.start();
        DistributedAtomicLong distributedAtomicLong = new DistributedAtomicLong(curatorFramework, "/DistributedAtomicLong", new RetryForever(1000));
        AtomicValue<Long> longAtomicValue = distributedAtomicLong.get();
        log.info("1. preValue={}, postValue={}, succeeded={}", longAtomicValue.preValue(), longAtomicValue.postValue(), longAtomicValue.succeeded());
        // 设置初始值，如果节点已经存在，则会返回false.
        boolean succeed = distributedAtomicLong.initialize(0L);
        log.info("initialize succeed? {}", succeed);
        longAtomicValue = distributedAtomicLong.get();
        log.info("2. preValue={}, postValue={}, succeeded={}", longAtomicValue.preValue(), longAtomicValue.postValue(), longAtomicValue.succeeded());
        // add 将增量添加到当前值并返回新值信息。请记住始终检查 AtomicValue.succeeded().
        distributedAtomicLong.add(10L);
        longAtomicValue = distributedAtomicLong.get();
        log.info("3. preValue={}, postValue={}, succeeded={}", longAtomicValue.preValue(), longAtomicValue.postValue(), longAtomicValue.succeeded());
        // subtract 从当前值中减去增量并返回新值信息。请记住始终检查 AtomicValue.succeeded().
        distributedAtomicLong.subtract(1L);
        longAtomicValue = distributedAtomicLong.get();
        log.info("4. preValue={}, postValue={}, succeeded={}", longAtomicValue.preValue(), longAtomicValue.postValue(), longAtomicValue.succeeded());
        // increment 加一
        distributedAtomicLong.increment();
        longAtomicValue = distributedAtomicLong.get();
        log.info("5. preValue={}, postValue={}, succeeded={}", longAtomicValue.preValue(), longAtomicValue.postValue(), longAtomicValue.succeeded());
        // decrement 减一
        distributedAtomicLong.decrement();
        longAtomicValue = distributedAtomicLong.get();
        log.info("6. preValue={}, postValue={}, succeeded={}", longAtomicValue.preValue(), longAtomicValue.postValue(), longAtomicValue.succeeded());
    }

    @Test
    public void testDistributedAtomicLongFailAndInterProcessMutex() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, new RetryForever(1000));
        curatorFramework.start();
        DistributedAtomicLong distributedAtomicLong;
        distributedAtomicLong = new DistributedAtomicLong(curatorFramework, "/DistributedAtomicLong"
                , new RetryForever(1000)
                , PromotedToLock.builder().lockPath("/DistributedAtomicLongPromotedToLock").timeout(3000, TimeUnit.MILLISECONDS).retryPolicy(new RetryOneTime(1000)).build());
        AtomicValue<Long> longAtomicValue = distributedAtomicLong.get();
        log.info("1. preValue={}, postValue={}, succeeded={}", longAtomicValue.preValue(), longAtomicValue.postValue(), longAtomicValue.succeeded());
        // 设置初始值，如果节点已经存在，则会返回false.
        boolean succeed = distributedAtomicLong.initialize(0L);
        log.info("initialize succeed? {}", succeed);
        longAtomicValue = distributedAtomicLong.get();
        log.info("2. preValue={}, postValue={}, succeeded={}", longAtomicValue.preValue(), longAtomicValue.postValue(), longAtomicValue.succeeded());
        // add 将增量添加到当前值并返回新值信息。请记住始终检查 AtomicValue.succeeded().
        distributedAtomicLong.add(10L);
        longAtomicValue = distributedAtomicLong.get();
        log.info("3. preValue={}, postValue={}, succeeded={}", longAtomicValue.preValue(), longAtomicValue.postValue(), longAtomicValue.succeeded());
        // subtract 从当前值中减去增量并返回新值信息。请记住始终检查 AtomicValue.succeeded().
        distributedAtomicLong.subtract(1L);
        longAtomicValue = distributedAtomicLong.get();
        log.info("4. preValue={}, postValue={}, succeeded={}", longAtomicValue.preValue(), longAtomicValue.postValue(), longAtomicValue.succeeded());
        // increment 加一
        distributedAtomicLong.increment();
        longAtomicValue = distributedAtomicLong.get();
        log.info("5. preValue={}, postValue={}, succeeded={}", longAtomicValue.preValue(), longAtomicValue.postValue(), longAtomicValue.succeeded());
        // decrement 减一
        distributedAtomicLong.decrement();
        longAtomicValue = distributedAtomicLong.get();
        log.info("6. preValue={}, postValue={}, succeeded={}", longAtomicValue.preValue(), longAtomicValue.postValue(), longAtomicValue.succeeded());
    }

    @Test
    public void testReentrantLock() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_HOST, RETRY_POLICY);
        curatorFramework.start();
        // 定义锁
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/program-talk-lock");
        // 获取锁
        lock.acquire();
        log.info("此处是业务代码");
        // 模拟业务执行30秒
        TimeUnit.SECONDS.sleep(30);
        // 释放锁
        lock.release();
    }

    @Test
    public void testCustomInterProcessMutex() throws Exception {
        InterProcessMutexThread task = new InterProcessMutexThread();
        Thread t1 = new Thread(task, "任务1");
        Thread t2 = new Thread(task, "任务2");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(60);
    }

    public class InterProcessMutexThread implements Runnable {
        String connectString = "localhost:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        @Override
        public void run() {
            CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
            curatorFramework.start();
            // 定义锁
            InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/InterProcessMutex");
            try {
                lock.acquire();
                String threadName = Thread.currentThread().getName();
                log.info("{} ，执行业务代码开始", threadName);
                TimeUnit.SECONDS.sleep(5);
                log.info("{} ，执行业务代码完毕", threadName);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
