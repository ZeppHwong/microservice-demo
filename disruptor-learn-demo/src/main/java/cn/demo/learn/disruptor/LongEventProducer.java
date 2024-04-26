package cn.demo.learn.disruptor;


import com.lmax.disruptor.RingBuffer;

// 生产者类，用于发布事件到 RingBuffer
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void publish(long value) {
        long sequence = ringBuffer.next(); // 请求下一个可用序号
        try {
            LongEvent event = ringBuffer.get(sequence); // 获取事件对象
            event.set(value); // 设置事件值
        } finally {
            ringBuffer.publish(sequence); // 发布事件，使其对消费者可见
        }
    }
}
