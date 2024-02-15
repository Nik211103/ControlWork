package JavaShop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyStore {
    private PriorityQueue<Toy> queue = new PriorityQueue<>((t1, t2) -> t2.frequency - t1.frequency);
    private Random random = new Random();

    public void put(String input) {
        String[] parts = input.split(" ");
        int id = Integer.parseInt(parts[0]);
        int frequency = Integer.parseInt(parts[1]);
        String name = parts[2];
        Toy toy = new Toy(id, name, frequency);
        queue.add(toy);
    }

    public int get() {
        int totalFrequency = queue.stream().mapToInt(t -> t.frequency).sum();
        int randomNumber = random.nextInt(totalFrequency);
        int sum = 0;
        for (Toy toy : queue) {
            sum += toy.frequency;
            if (randomNumber < sum) {
                return toy.id;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        toyStore.put("1 2 конструктор;");
        toyStore.put("2 2 робот;");
        toyStore.put("3 6 кукла;");

        try {
            FileWriter writer = new FileWriter("output.txt");
            for (int i = 0; i < 10; i++) {
                writer.write(String.valueOf(toyStore.get()));
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
