package algorthims.LeetCode.Mock;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 *
 *
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */

import java.util.*;

public class EvaluateDivide {

    //Did not verified as leetcode 399 problem
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, List<String[]>> map = new HashMap<>();

        //build the graph
        buildGraph(map, equations, values);

        double[] result = new double[queries.size()];

        for (int index = 0; index < queries.size(); index++) {
            List<String> query = queries.get(index);
            Set<String> set = new HashSet<>();
            if( map.get( query.get(0) ) == null || map.get(query.get(1)) == null ){
                result[index] = -1.0;
                continue;
            }
            result[index] = traverse(map, query.get(0), query.get(1), 1.0, set);
        }

        return result;
    }

    public static double traverse(Map<String, List<String[]>> map, String start, String end, double value, Set<String> check) {


        if (start == end) {
            return value;
        }

        check.add(start);

        List<String[]> lst = map.get(start);
        double res = Double.MIN_VALUE;

        for (String[] neighbors : lst) {

            if (check.contains(neighbors[0])) {
                continue;
            }
            value = value * Double.parseDouble(neighbors[1]);

            double temp =  traverse(map, neighbors[0], end, value, check);
            if( temp != Double.MIN_VALUE ){
                res = temp;
            }

        }

        return res;
    }

    //Method to build the graph
    public static void buildGraph(Map<String, List<String[]>> map, List<List<String>> equations, double[] values) {

        for (int index = 0; index < equations.size(); index++) {

            List<String> equation = equations.get(index);

            String value = String.valueOf(values[index]);
            String first = equation.get(0);
            String second = equation.get(1);

            //first list
            List<String[]> lst1 = map.getOrDefault(first, new ArrayList<>());
            lst1.add(new String[]{
                    second, value
            });
            map.put(first, lst1);

            //Second list
            value = String.valueOf(1 / Double.parseDouble(value));
            List<String[]> lst2 = map.getOrDefault(second, new ArrayList<>());
            lst2.add(new String[]{
                    first, value
            });
            map.put(second, lst2);


        }
    }

    public static void main(String[] args) {

        List<List<String>> lst = new ArrayList<>();
        List<String> temp1 = new ArrayList<>();
        List<String> temp2 = new ArrayList<>();

        temp1.add("a");
        temp1.add("b");
        temp2.add("b");
        temp2.add("c");
        lst.add(temp1);
        lst.add(temp2);

        double[] values = new double[]{2.0, 3.0};

        List<List<String>> lst2 = new ArrayList<>();
        List<String> temp3 = new ArrayList<>();
        List<String> temp4 = new ArrayList<>();
        List<String> temp5 = new ArrayList<>();
        List<String> temp6 = new ArrayList<>();
        List<String> temp7 = new ArrayList<>();

        temp3.add("a");
        temp3.add("c");
        temp4.add("b");
        temp4.add("a");
        temp5.add("a");
        temp5.add("e");
        temp6.add("a");
        temp6.add("a");
        temp7.add("e");
        temp7.add("e");

        lst2.add(temp3);
        lst2.add(temp4);
        lst2.add(temp5);
        lst2.add(temp6);
        lst2.add(temp7);


        double[] res = calcEquation(lst, values, lst2);

        for (double num : res) {
            System.out.println(num);
        }
    }
}
