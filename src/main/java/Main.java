import lombok.SneakyThrows;

/**
 * @author Kozka Ivan
 */
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        HashMap<String, Integer> goodsList = new HashMap<>();

        goodsList.put("milk", 1);
        goodsList.put("bread", 2);
        goodsList.put("nachos", 123);
        goodsList.put("salt", 500);

        for (int i : goodsList.values()) {
            System.out.println(i);
        }
    }
}
