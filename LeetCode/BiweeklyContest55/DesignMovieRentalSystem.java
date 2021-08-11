package LeetCode.BiweeklyContest55;

import java.util.*;

public class DesignMovieRentalSystem {
   public static void main(String[] args) {

       DesignMovieRentalSystem o = new DesignMovieRentalSystem(3, new int[][]{
               {0, 1, 5},{0, 2, 6},{0, 3, 7},{1, 1, 4},{1, 2, 7},{2, 1, 5}
       });
       System.out.println(o.search(1));
    o.rent(0,1);
       o.rent(1,2);
       System.out.println(o.report());
       o.drop(1, 2);
       System.out.println(o.search(2));
   }

   Map<Integer, TreeSet<Node>> database = new HashMap<>();
   Map<MoviePrice, Integer> prices = new HashMap<>();

   TreeSet<Node> report = new TreeSet<>(((o1, o2) -> {
      if (o1.price != o2.price)
         return Integer.compare(o1.price, o2.price);
      if (o1.shop != o2.shop)
         return Integer.compare(o1.shop, o2.shop);
      return Integer.compare(o1.movie, o2.movie);
   }));

   public DesignMovieRentalSystem(int n, int[][] entries) {
      for (int[] entry : entries) {
         Node curr = new Node(entry[1], entry[0], entry[2]);
         int movie = entry[1];
         TreeSet<Node> set = database.getOrDefault(movie, new TreeSet<>((o1, o2) -> {
            if (o1.price != o2.price)
               return Integer.compare(o1.price, o2.price);
            return Integer.compare(o1.shop, o2.shop);
         }));
         set.add(curr);
         database.put(movie, set);
         //Add the prices
         prices.put(new MoviePrice(entry[0], entry[1]), entry[2]);
      }
   }

   public List<Integer> search(int movie) {
      List<Integer> list = new ArrayList<Integer>();
      if (database.containsKey(movie)) {
         TreeSet<Node> set = database.get(movie);
         for (Node curr : set) {
            list.add(curr.shop);
            if (list.size() == 5)
               return list;
         }
      }
      return list;
   }

   public void rent(int shop, int movie) {
      int price = prices.get(new MoviePrice(shop, movie));
      database.get(movie).remove(new Node(movie, shop, price));
      report.add(new Node(movie, shop, price));
   }

   public void drop(int shop, int movie) {
      int price = prices.get(new MoviePrice(shop, movie));
      database.get(movie).add(new Node(movie, shop, price));
      report.remove(new Node(movie, shop, price));
   }

   public List<List<Integer>> report() {
      List<List<Integer>> res = new ArrayList<>();
      for (Node curr : report) {
         res.add(Arrays.asList(curr.shop, curr.movie));
         if(res.size() == 5) return res;
      }
      return res;
   }

   static class Node {
      int movie, shop, price;

      public Node(int m, int s, int p) {
         movie = m;
         shop = s;
         price = p;
      }

       @Override
       public boolean equals(Object o) {
           if (this == o) return true;
           if (o == null || getClass() != o.getClass()) return false;
           Node node = (Node) o;
           return movie == node.movie && shop == node.shop && price == node.price;
       }

       @Override
       public int hashCode() {
           return Objects.hash(movie, shop, price);
       }
   }

   static class MoviePrice {
      int shop, movie;

      public MoviePrice(int s, int m) {
         shop = s;
         movie = m;
      }

       @Override
       public boolean equals(Object o) {
           if (this == o) return true;
           if (o == null || getClass() != o.getClass()) return false;
           MoviePrice that = (MoviePrice) o;
           return shop == that.shop && movie == that.movie;
       }

       @Override
       public int hashCode() {
           return Objects.hash(shop, movie);
       }
   }
}
