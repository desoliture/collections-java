import lombok.SneakyThrows;

import java.lang.reflect.Method;

/**
 * @author Kozka Ivan
 */
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        TreeMap<String, Integer> goods = new TreeMap<>();

        goods.put("milk", 2);
        goods.put("jopa", 4);
        goods.put("pizdec", 1);
        goods.put("Andrei", 10);
        goods.put("Joja", 1);
        goods.put("bread", 4);

        goods.remove("Joja");

        for (var e : goods) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }

//        Method m = goods.getClass().getDeclaredMethod("getChildren", TreeMap.Node.class, List.class);
//        m.setAccessible(true);
//
//        var list = (ArrayList<TreeMap.Node<String, Integer>>) m.invoke(goods, goods.root, new ArrayList<TreeMap.Node<String, Integer>>());
//
//        for (var e : list) {
//            System.out.println(e.key + " " + e.value);
//        }

    }
}
