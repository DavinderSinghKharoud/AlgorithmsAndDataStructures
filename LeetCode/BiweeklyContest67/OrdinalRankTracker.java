package LeetCode.BiweeklyContest67;

import java.util.*;

/**
 * TimeNeededToBuyTickets scenic location is represented by its name and attractiveness score, where
 * name is a unique string among all locations and score is an integer.
 * Locations can be ranked from the best to the worst. The higher the score, the
 * better the location. If the scores of two locations are equal, then the
 * location with the lexicographically smaller name is better.
 * <p>
 * You are building a system that tracks the ranking of locations with the
 * system initially starting with no locations. It supports:
 * <p>
 * Adding scenic locations, one at a time. Querying the ith best location of all
 * locations already added, where i is the number of times the system has been
 * queried (including the current query). For example, when the system is
 * queried for the 4th time, it returns the 4th best location of all locations
 * already added. Note that the test data are generated so that at any time, the
 * number of queries does not exceed the number of locations added to the
 * system.
 * <p>
 * Implement the SORTracker class:
 * <p>
 * SORTracker() Initializes the tracker system. void add(string name, int score)
 * Adds a scenic location with name and score to the system. string get()
 * Queries and returns the ith best location, where i is the number of times
 * this method has been invoked (including this invocation).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input ["SORTracker", "add", "add", "get", "add", "get", "add", "get", "add",
 * "get", "add", "get", "get"] [[], ["bradford", 2], ["branford", 3], [],
 * ["alps", 2], [], ["orland", 2], [], ["orlando", 3], [], ["alpine", 2], [],
 * []] Output [null, null, null, "branford", null, "alps", null, "bradford",
 * null, "bradford", null, "bradford", "orland"]
 * <p>
 * Explanation SORTracker tracker = new SORTracker(); // Initialize the tracker
 * system. tracker.add("bradford", 2); // Add location with name="bradford" and
 * score=2 to the system. tracker.add("branford", 3); // Add location with
 * name="branford" and score=3 to the system. tracker.get(); // The sorted
 * locations, from best to worst, are: branford, bradford. // Note that branford
 * precedes bradford due to its higher score (3 > 2). // This is the 1st time
 * get() is called, so return the best location: "branford". tracker.add("alps",
 * 2); // Add location with name="alps" and score=2 to the system.
 * tracker.get(); // Sorted locations: branford, alps, bradford. // Note that
 * alps precedes bradford even though they have the same score (2). // This is
 * because "alps" is lexicographically smaller than "bradford". // Return the
 * 2nd best location "alps", as it is the 2nd time get() is called.
 * tracker.add("orland", 2); // Add location with name="orland" and score=2 to
 * the system. tracker.get(); // Sorted locations: branford, alps, bradford,
 * orland. // Return "bradford", as it is the 3rd time get() is called.
 * tracker.add("orlando", 3); // Add location with name="orlando" and score=3 to
 * the system. tracker.get(); // Sorted locations: branford, orlando, alps,
 * bradford, orland. // Return "bradford". tracker.add("alpine", 2); // Add
 * location with name="alpine" and score=2 to the system. tracker.get(); //
 * Sorted locations: branford, orlando, alpine, alps, bradford, orland. //
 * Return "bradford". tracker.get(); // Sorted locations: branford, orlando,
 * alpine, alps, bradford, orland. // Return "orland".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * name consists of lowercase English letters, and is unique among all
 * locations. 1 <= name.length <= 10 1 <= score <= 105 At any time, the number
 * of calls to get does not exceed the number of calls to add. At most 4 * 104
 * calls in total will be made to add and get.
 */
public class OrdinalRankTracker {
    public static void main(String[] args) {
        OrdinalRankTracker tracker = new OrdinalRankTracker();
        tracker.SORTracker();// Initialize the tracker system.
        tracker.add2("x", 56); // Add location with name="bradford" and score=2 to the system.
        System.out.println(tracker.get2());
        tracker.add2("t", 93); // Add location with name="branford" and score=3 to the system.
        tracker.add2("q", 32);
        System.out.println(tracker.get2()); // The sorted locations, from best to worst, are: branford, bradford.
        // Note that branford precedes bradford due to its higher score (3 > 2).
        // This is the 1st time get() is called, so return the best location:
        // "branford".
        tracker.add2("alps", 2); // Add location with name="alps" and score=2 to the system.
        System.out.println(tracker.get2()); // Sorted locations: branford, alps, bradford.
        // Note that alps precedes bradford even though they have the same score (2).
        // This is because "alps" is lexicographically smaller than "bradford".
        // Return the 2nd best location "alps", as it is the 2nd time get() is called.
        tracker.add2("orland", 2); // Add location with name="orland" and score=2 to the system.
        System.out.println(tracker.get2()); // Sorted locations: branford, alps, bradford, orland.
        // Return "bradford", as it is the 3rd time get() is called.
        tracker.add2("orlando", 3); // Add location with name="orlando" and score=3 to the system.
        System.out.println(tracker.get2()); // Sorted locations: branford, orlando, alps, bradford, orland.
        // Return "bradford".
        tracker.add2("alpine", 2); // Add location with name="alpine" and score=2 to the system.
        System.out.println(tracker.get2()); // Sorted locations: branford, orlando, alpine, alps, bradford, orland.
        // Return "bradford".
        System.out.println(tracker.get2());
    }

    PriorityQueue<Node> minHeap, maxHeap;

    public OrdinalRankTracker() {
        minHeap = new PriorityQueue<>((o1, o2) -> {
            if (o1.rank == o2.rank) {
                return o2.s.compareTo(o1.s);
            }
            return Integer.compare(o1.rank, o2.rank);
        });
        maxHeap = new PriorityQueue<>((o1, o2) -> {
            if (o1.rank == o2.rank) {
                return o1.s.compareTo(o2.s);
            }
            return Integer.compare(o2.rank, o1.rank);
        });
    }

    public void add(String name, int score) {
        minHeap.add(new Node(name, score));
        maxHeap.add(minHeap.poll());
    }

    public String get() {
        minHeap.add(maxHeap.poll());
        return minHeap.peek().s;
    }

    static class Node {
        String s;
        int rank;

        public Node(String s, int rank) {
            this.s = s;
            this.rank = rank;
        }
    }

    /************************************************************** */
    TreeSet<Node> set;
    Node last;

    public void SORTracker() {
        set = new TreeSet<>((o1, o2) -> {
            if (o1.rank == o2.rank) {
                return o2.s.compareTo(o1.s);
            }
            return Integer.compare(o1.rank, o2.rank);
        });
    }

    public void add2(String name, int score) {
        Node curr = new Node(name, score);
        set.add(curr);

        if (last == null) {
            last = set.first();
        } else if (curr.rank > last.rank || (curr.rank == last.rank && last.s.compareTo(curr.s) > 0)) {
            last = set.higher(last);
            //System.out.println(last);
        }
    }


    public String get2() {
        String ans = last.s;
        last = set.lower(last);
        return ans;
    }

}
