package demo2;

/**
 * 题目：多线程8锁
 * 1 标准访问，请问先打印邮件还是短信
 * 2 邮件方法暂停4秒钟，请问先打印邮件还是短信
 * 3 新增一个普通方法hello()，请问先打印邮件还是hello()
 * 4 两部手机，请问先打印邮件还是短信
 * 5 两个静态同步方法，同一部手机，请问先打印邮件还是短信
 * 6 两个静态同步方法，两部手机，请问先打印邮件还是短信
 * 7 1一普通同步方法，1个静态同步方法，1部手机，请问先打印邮件还是短信
 * 8 1一普通同步方法，1个静态同步方法，2部手机，请问先打印邮件还是短信
 *
 *
 *
 * 总结：
 * 1、2 一个对象里面如果有多个synchronized方法，某一时刻内，只要一个线程去调用其中的一个synchronized方法了，
 *      其他的线程都只能等待，换句话说，某一时刻内，只能有唯一一个线程去访问这些synchronized方法
 *      锁的是当前对象this，被锁定后，其他的线程都不能进入到对象的其他的synchronized方法
 * 3    加个普通方法后和同步锁无关
 * 4    换成两个对象后，不是同一把锁，互不干扰
 * 5、6  所有的静态同步方法用的也是同一把锁---类对象本身
 *       一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁
 *       而不管是同一实例对象的静态同步方法之间，还是不同的实例对象的静态同步方法之间，只要他们同一个类的实例对象
 * 7    这两把锁(this、class)是两个不同的对象，所以静态同步方法与普通静态同步方法是不会有竞争条件的
 *
 *  所有的非静态同步方法用的都是同一把锁---实例对象本身
 *  synchronized实现同步的基础：Java中的每一个对象都可以作为锁
 *  具体表现为以下3中形式：
 *      对于普通同步方法，锁是当前对象实例
 *      对于静态同步方法，锁是当前类的Class对象
 *      对于同步方法块，锁是synchronized括号里的配置对象
 */
public class Lock8 {
    public static void main(String[] args) throws Exception{
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            }catch (Exception e){
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
//                phone.sendSMS();
//                phone.hello();
                phone2.sendSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        }, "B").start();

    }
}

class Phone{
    public static synchronized void sendEmail() throws Exception{
        Thread.sleep(4000);
        System.out.println("-----sendEmail");
    }

    public synchronized void sendSMS(){
        System.out.println("-----sendSMS");
    }

    public void hello(){
        System.out.println("-----Hello");
    }
}