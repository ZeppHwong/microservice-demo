package cn.demo.learn.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class DisruptorExample {
    public static void main(String[] args) {
        // 定义 Disruptor 的配置参数
        int ringBufferSize = 1024; // 环形缓冲区大小
        String disruptorName = "LongEventDisruptor"; // Disruptor 名称（用于线程命名）

        // 创建 Disruptor 实例
        Disruptor<LongEvent> disruptor = new Disruptor<>(new LongEventFactory(), ringBufferSize,
                r -> new Thread(r, disruptorName)
        ); // 使用阻塞等待策略（可替换为其他策略）


        // 添加事件处理器（消费者）
        disruptor.handleEventsWith(new LongEventHandler());
        // 启动 Disruptor，开始处理事件
        disruptor.start();
        // 获取 RingBuffer，用于生产者发布事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        // 创建生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);

        // 发布若干个事件
        for (long i = 0; i < 100; i++) {
            producer.publish(i);
        }

        // 关闭 Disruptor，等待所有事件处理完毕
        disruptor.shutdown();
    }
}