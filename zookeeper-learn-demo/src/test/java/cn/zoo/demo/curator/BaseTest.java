package cn.zoo.demo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
    public final Logger log = LoggerFactory.getLogger(this.getClass().getName());
    public static final String CONNECT_HOST = "localhost:2181";
    public final RetryPolicy RETRY_POLICY = new ExponentialBackoffRetry(1000, 3);
}
