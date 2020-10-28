package demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在高内聚低耦合的前提下：线程    操作   资源类
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 60; i++) {
                    ticket.saleTicket();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 60; i++) {
                    ticket.saleTicket();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 60; i++) {
                    ticket.saleTicket();
                }
            }
        }, "C").start();*/

        new Thread(() -> {
            for (int i = 0; i <= 60; i++) ticket.saleTicket();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i <= 60; i++) ticket.saleTicket();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i <= 60; i++) ticket.saleTicket();
        }, "C").start();
    }
}

//资源类
class Ticket {
    private int number = 50;
    private final Lock lock = new ReentrantLock();

    public void saleTicket() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第:" + (number--) + "\t还剩下:" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
