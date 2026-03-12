import java.util.*;

public class HyderabadCityExplorer {

    static class Place {
        String name;
        double rating;
        String category;

        Place(String name, double rating, String category) {
            this.name = name;
            this.rating = rating;
            this.category = category;
        }

        @Override
        public String toString() {
            return String.format("[%s] %-20s | Rating: %.1f", category, name, rating);
        }
    }

    // --- CO1: Classical Sorting (Merge Sort) ---
    public static void mergeSortByRating(Place[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSortByRating(arr, l, m);
            mergeSortByRating(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(Place[] arr, int l, int m, int r) {
        int n1 = m - l + 1, n2 = r - m;
        Place[] L = new Place[n1]; Place[] R = new Place[n2];
        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i].rating >= R[j].rating) arr[k++] = L[i++]; 
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // --- New Method: Filter and Display ---
    public static void displayByCategory(Place[] arr, String category) {
        System.out.println("\n--- Popular " + category + " in Hyderabad ---");
        boolean found = false;
        for (Place p : arr) {
            if (p.category.equalsIgnoreCase(category)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) System.out.println("No entries found for this category.");
    }

    public static void main(String[] args) {
        // 1. Expanded Data Initialization
        Place[] locations = {
            new Place("Charminar", 4.7, "Place"),
            new Place("Golconda Fort", 4.8, "Place"),
            new Place("Paradise Biryani", 4.9, "Food"),
            new Place("Shah Ghouse", 4.7, "Food"),
            new Place("Roastery Coffee", 4.8, "Cafe"),
            new Place("Autumn Leaf Cafe", 4.5, "Cafe"),
            new Place("Salar Jung Museum", 4.6, "Place"),
            new Place("Ram Ki Bandi", 4.4, "Food"),
            new Place("Concu", 4.7, "Cafe"),
            new Place("Hussain Sagar", 4.4, "Place")
        };

        // 2. Sort once at the start (O(n log n))
        mergeSortByRating(locations, 0, locations.length - 1);

        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("=== WELCOME TO HYDERABAD CITY EXPLORER ===");
        
        do {
            System.out.println("\nSelect an option to explore:");
            System.out.println("1. View Popular Places (Heritage/Parks)");
            System.out.println("2. View Popular Foods (Biryani/Street Food)");
            System.out.println("3. View Popular Cafes");
            System.out.println("4. View All (Sorted by Rating)");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: displayByCategory(locations, "Place"); break;
                case 2: displayByCategory(locations, "Food"); break;
                case 3: displayByCategory(locations, "Cafe"); break;
                case 4:
                    System.out.println("\n--- All Hyderabad Attractions (Top Rated) ---");
                    for (Place p : locations) System.out.println(p);
                    break;
                case 5: System.out.println("Happy Exploring! Goodbye."); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
        
        sc.close();
    }
}
