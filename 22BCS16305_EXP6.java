import java.util.*;
import java.util.stream.Collectors;

class Worker {
    String name;
    int age;
    double salary;

    Worker(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " | Age: " + age + " | Salary: " + salary;
    }
}

class Learner {
    String name;
    double marks;

    Learner(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " | Marks: " + marks;
    }
}

class Item {
    String name;
    String category;
    double price;

    Item(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}

public class EXP6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. Sort Workers by Salary");
            System.out.println("2. Filter Learners scoring above 75% and sort by marks");
            System.out.println("3. Process Items Dataset");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Worker> workers = Arrays.asList(
                            new Worker("Alice", 30, 50000),
                            new Worker("Bob", 25, 60000),
                            new Worker("Charlie", 35, 55000)
                    );
                    workers.sort(Comparator.comparingDouble(wrk -> wrk.salary));
                    workers.forEach(System.out::println);
                    break;

                case 2:
                    List<Learner> learners = Arrays.asList(
                            new Learner("John", 85),
                            new Learner("Doe", 65),
                            new Learner("Jane", 90)
                    );
                    learners.stream()
                            .filter(l -> l.marks > 75)
                            .sorted(Comparator.comparingDouble(l -> -l.marks))
                            .forEach(System.out::println);
                    break;

                case 3:
                    List<Item> items = Arrays.asList(
                            new Item("Laptop", "Electronics", 800),
                            new Item("Phone", "Electronics", 500),
                            new Item("Shirt", "Clothing", 50),
                            new Item("Jeans", "Clothing", 60)
                    );

                    System.out.println("\nItems grouped by category:");
                    Map<String, List<Item>> groupedByCategory = items.stream()
                            .collect(Collectors.groupingBy(i -> i.category));
                    groupedByCategory.forEach((category, list) -> {
                        System.out.println(category + ": " + list);
                    });

                    System.out.println("\nMost expensive item in each category:");
                    Map<String, Optional<Item>> mostExpensiveInCategory = items.stream()
                            .collect(Collectors.groupingBy(i -> i.category,
                                    Collectors.maxBy(Comparator.comparingDouble(i -> i.price))));
                    mostExpensiveInCategory.forEach((category, item) -> {
                        System.out.println(category + ": " + item.get().name + " - $" + item.get().price);
                    });

                    double avgPrice = items.stream()
                            .collect(Collectors.averagingDouble(i -> i.price));
                    System.out.println("\nAverage price of all items: " + avgPrice);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
