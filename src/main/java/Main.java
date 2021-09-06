import lombok.SneakyThrows;

import java.util.LinkedList;

/**
 * @author Kozka Ivan
 */
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        /*LinkedList<Integer> ints = new LinkedList<>();

        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);

        System.out.println(ints.toString());

        ints.add(0, 0);

        System.out.println(ints.toString());

        ints.add(1, 2);

        System.out.println(ints.toString());
*/
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.toString();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        for (int numb :
                numbers) {
            System.out.println(numb);
        }

    }
}
