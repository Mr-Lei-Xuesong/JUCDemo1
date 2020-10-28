package demo1;

public class LambdaExpressDemo {
    public static void main(String[] args) {
        Foo foo = (x, y) -> {
            System.out.println(x + "加" + y);
            return x + y;
        };
        System.out.println(foo.add(3, 5));
        System.out.println(foo.sum(2, 5));
        System.out.println(Foo.div(10, 2));
    }
}

@FunctionalInterface
interface Foo {
    int add(int x, int y);

    default int sum(int x, int y) {
        System.out.println(x + "乘" + y);
        return x * y;
    }

    static int div(int x, int y) {
        System.out.println(x + "除" + y);
        return x / y;
    }

}