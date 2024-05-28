import java.util.ArrayList;
import java.util.List;

public class Prog6 {
    public static void main(String[] args) {
        // Creating two lists
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        // Adding elements to the lists
        list1.add("Apple");
        list1.add("Banana");
        list1.add("Orange");

        list2.add("Mango");
        list2.add("Grapes");

        // Displaying the initial contents of the lists
        System.out.println("Initial contents of list1: " + list1);
        System.out.println("Initial contents of list2: " + list2);

        // Updating elements in list1
        list1.set(1, "Pineapple");

        // Adding elements to list2 at specific index
        list2.add(1, "Watermelon");

        // Displaying the updated contents of the lists
        System.out.println("Updated contents of list1: " + list1);
        System.out.println("Updated contents of list2: " + list2);
    }
}
