package demo3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("***** come in here *****");
        Thread.sleep(4000);
        return 1024;
    }
}

/**
 * 多线程获得第三种方式
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "A").start();
        System.out.println(Thread.currentThread().getName() + "主线程完成");
        System.out.println(futureTask.get());
    }
}
