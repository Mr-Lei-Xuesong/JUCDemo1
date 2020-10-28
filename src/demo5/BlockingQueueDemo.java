package demo5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * 阻塞：必须阻塞/不得不阻塞
 * <p>
 * 栈->       后进先出
 * 队列->     先进先出
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

//        当阻塞队列满时，队列会阻塞生产者线程一定时间，超过限时后生产者线程会退出
//        System.out.println(blockingQueue.offer("d", 3, TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

//        当阻塞队列空时，队列会阻塞消费者线程一定时间，超过限时后消费者线程会退出
//        System.out.println(blockingQueue.poll(3,TimeUnit.SECONDS));
    }

    private static void putAndTake(BlockingQueue<String> blockingQueue) throws InterruptedException {
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

//        当阻塞队列满时，生产者线程继续往队列里put元素，队列会一直阻塞生产者线程直到put数据or响应中断退出
//        blockingQueue.put("d");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();

//        当阻塞队列空时，消费者线程从队列里take元素，队列会一直阻塞消费者线程直到队列可用
//        blockingQueue.take();
    }

    private static void offerAndPoll(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

//        插入方法，成功true 失败false
//        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

//        移除方法，成功返回出队列元素，队列里没有元素就返回null
//        System.out.println(blockingQueue.poll());

//        检查队首元素
//        System.out.println(blockingQueue.peek());

    }

    private static void addAndRemove(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

//        队列满时再添加元素会报：java.lang.IllegalStateException: Queue full
//        System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

//        队列空时再移除元素会报：java.util.NoSuchElementException
//        System.out.println(blockingQueue.remove());

//        检查队首元素
//        System.out.println(blockingQueue.element());
    }
}
