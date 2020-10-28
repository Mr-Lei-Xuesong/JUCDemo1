package demo3;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 题目：请举例说明集合类是不安全的
 * <p>
 * 1. 故障现象
 * java.util.ConcurrentModificationException
 * <p>
 * 2. 导致原因
 * <p>
 * 3. 解决方案
 * 3.1  Vector
 * 3.2  Collections.synchronizedList(new ArrayList<>())
 * 3.3  CopyOnWriteArrayList<>()
 * 4. 优化建议(同样的错误，不会出现2次)
 */
public class NotSafeDemo {
    public static void main(String[] args) {
        setNotSafe();
    }

    private static void mapNotSafe() {
        Map<String, String> map1 = new HashMap<>();
//        Map<String, String> map=Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map=new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
//        Set<String> set= new HashSet<>();
//        Set<String> set=Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    public static void listNotSafe() {
//        List<String> list= new ArrayList<>();
//        List<String> list= new Vector<>();
//        List<String> list= Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
/*
写时复制(读方法不加锁，写方法加锁)
CopyOnWrite容器即写时复制的容器，往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行copy
复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，添加元素之后，
再将原容器的引用指向新的容器setArray(newElements)；这样做的好处是可以对CopyOnWrite容器进行并发的读，
而不需要加锁，因为当前容器不会添加任何元素，所以CopyOnWrite容器也是一种读写分离的意思，读和写不同容器

public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }
 */