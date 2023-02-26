import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        List<T> items = stream.sorted(order).collect(Collectors.toList());
        if (!items.isEmpty()) {
            minMaxConsumer.accept(items.get(0), items.get(items.size() - 1));

        } else {
            minMaxConsumer.accept(null, null);
        }
    }

    public static void main(String[] args) {

        Stream<Integer> stream = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 15, 21)).stream();

        findMinMax(
                stream,
                Comparator.naturalOrder(),
                (x, y) -> System.out.printf("min: %s, max: %s%n", x, y)
        );
        stream.close();

// Задача 2

        Stream<Integer> stream1 = new ArrayList<>(Arrays.asList(1, 2, 5, 8, 9, 16, 21)).stream();
        System.out.println("Количество четных чисел " + countEvenNumber(stream1.collect(Collectors.toList())));

    }

    public static int countEvenNumber(List<Integer> integers) {
        return (int) integers.stream()
                .filter(i -> i % 2 == 0)
                .peek(System.out::println)
                .count();
    }

}
