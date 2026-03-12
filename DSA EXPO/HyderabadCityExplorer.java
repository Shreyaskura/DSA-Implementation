import java.util.*;

// Main Class: Hyderabad City Explorer
public class HyderabadCityExplorer {

    // PO2/CO2: Place Object with Rating and Category
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
            return String.format("[%s] %-15s | Rating: %.1f", category, name, rating);
        }
    }

    // --- CO2: ADT Implementation (Doubly Linked List for Navigation) ---
    static class NavigationNode {
        Place place;
        NavigationNode next, prev;
        NavigationNode(Place p) { this.place = p; }
    }

    private NavigationNode head, tail;
    public void addPlaceToTour(Place p) {
        NavigationNode newNode = new NavigationNode(p);
        if (head == null) head = tail = newNode;
        else { tail.next = newNode; newNode.prev = tail; tail = newNode; }
    }

    // --- CO1: Classical Sorting (Merge Sort - Recursive) ---
    // Time Complexity: O(n log n), Space Complexity: O(n)
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
            if (L[i].rating >= R[j].rating) arr[k++] = L[i++]; // Descending
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // --- MAIN EXECUTION ---
    public static void main(String[] args) {
        System.out.println("=== HYDERABAD CITY EXPLORER (DSA PROJECT) ===\n");

        // 1. Data Initialization
        Place[] locations = {
            new Place("Charminar", 4.7, "Heritage"),
            new Place("Golconda Fort", 4.8, "Heritage"),
            new Place("Biryani King", 4.9, "Food"),
            new Place("Salar Jung", 4.6, "Museum"),
            new Place("Hussain Sagar", 4.4, "Park")
        };

        // 2. CO1: Ranking Locations (Sorting)
        mergeSortByRating(locations, 0, locations.length - 1);
        System.out.println(">> Sorted Attractions by Rating (Merge Sort):");
        for (Place p : locations) System.out.println(p);

        // 3. CO3: Real-world Workflows (Queues & Priority Queues)
        // Ticket Booking (FIFO)
        Queue<String> ticketQueue = new LinkedList<>();
        ticketQueue.add("Tourist_01");
        ticketQueue.add("Tourist_02");
        System.out.println("\n>> Ticket Queue Head: " + ticketQueue.peek() + " is next for Charminar.");

        // VIP/Emergency Alerts (Heap/Priority Queue)
        PriorityQueue<String> emergencyAlerts = new PriorityQueue<>(Collections.reverseOrder());
        emergencyAlerts.add("Traffic Jam at Banjara Hills");
        emergencyAlerts.add("URGENT: VIP Movement at Assembly");
        System.out.println(">> Processing Priority Alert: " + emergencyAlerts.poll());

        // 4. CO4: Fast Lookups (Hashing)
        HashMap<String, String> quickFacts = new HashMap<>();
        quickFacts.put("Charminar", "Built in 1591 by Muhammad Quli Qutb Shah.");
        quickFacts.put("Golconda", "Famous for its acoustic effects.");
        
        System.out.println("\n>> Instant Search (Hashing) for 'Charminar':");
        System.out.println("Fact: " + quickFacts.get("Charminar"));

        // 5. CO3: Navigation History (Stack - LIFO)
        Stack<String> visitHistory = new Stack<>();
        visitHistory.push("Charminar");
        visitHistory.push("Biryani King");
        System.out.println("\n>> History Stack: User just left " + visitHistory.pop() + " to go back.");
    }
}
