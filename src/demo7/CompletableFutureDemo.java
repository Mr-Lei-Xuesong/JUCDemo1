package demo7;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回值");
        });
        voidCompletableFuture.get();

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回值");
//            int age = 10 / 0;
            return 1024;
        });

        integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("****t:" + t);
            System.out.println("****u:" + u);
        }).exceptionally(f->{
            System.out.println("****exception:"+f.getMessage());
            return 404;
        }).get();
    }
}
