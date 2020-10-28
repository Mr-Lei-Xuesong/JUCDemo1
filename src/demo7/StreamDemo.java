package demo7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 按照给出的数据，找出同时满足以下条件的用户
 * 偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序且只输出一个用户名
 */
public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream().
                filter(t -> t.getId() % 2 == 0).//偶数ID
                filter(t -> t.getAge() > 24).//年龄大于24
                map(t -> t.getName().toUpperCase()).//转大写
                sorted(Comparator.reverseOrder()).//逆序排序
                limit(1).//只输出一个
                forEach(System.out::println);//输出数据

    }

    private static void functionInterface() {
        //供给型接口
        /*Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "Java。。。";
            }
        };*/
        Supplier<String> supplier = () -> {
            return "Java。。。";
        };
        System.out.println(supplier.get());

        //消费型接口
        /*Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };*/
        Consumer<String> consumer = s -> {
            System.out.println(s);
        };
        consumer.accept("Linux。。。");

        //函数型接口
        /*Function<String,Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };*/
        Function<String, Integer> function = s -> {
            return s.length();
        };
        System.out.println(function.apply("MySQL。。。"));

        //断定型接口
        /*Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };*/
        Predicate<String> predicate = s -> {
            return s.isEmpty();
        };
        System.out.println(predicate.test("HTML。。。"));
    }
}

class User {
    private int id;
    private String name;
    private int age;

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
