package LeetCode.BiweeklyContest68;

import java.util.*;

public class FindPossibleRecipes {
    public static void main(String[] args) {
        FindPossibleRecipes o = new FindPossibleRecipes();
        System.out.println(o.findAllRecipes(new String[]{"ju", "fzjnm", "x", "e", "zpmcz", "h", "q"},
                Arrays.asList(Arrays.asList("d"), Arrays.asList("hveml", "f", "cpivl"),
                        Arrays.asList("cpivl", "zpmcz", "h", "e", "fzjnm", "ju"), Arrays.asList("cpivl", "hveml", "zpmcz", "ju", "h"),
                        Arrays.asList("h", "fzjnm", "e", "q", "x"), Arrays.asList("d", "hveml", "cpivl", "q", "zpmcz", "ju", "e", "x"),
                        Arrays.asList("f", "hveml", "cpivl"))
                , new String[]{"f", "hveml", "cpivl", "d"}));
    }


    Set<String> supp = new HashSet<>();
    Map<String, Boolean> vis = new HashMap<>();
    Map<String, List<String>> rec = new HashMap<>();

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        supp.addAll(Arrays.asList(supplies));
        for (int i = 0; i < recipes.length; i++) {
            rec.put(recipes[i], ingredients.get(i));
        }
        List<String> ans = new ArrayList<>();
        for (String recipe : recipes) {
            if (isPossible(recipe, new HashSet<>()))
                ans.add(recipe);
        }
        return ans;
    }

    boolean isPossible(String recipe, Set<String> inCircle) {
        if (inCircle.contains(recipe)) return false;
        if (vis.containsKey(recipe))
            return vis.get(recipe);
        if (supp.contains(recipe))
            return true;
        inCircle.add(recipe);
        List<String> ing = rec.get(recipe);
        if (ing == null)
            return false;

        boolean isPoss = true;
        for (String i : ing) {
            if (!isPossible(i, inCircle)) {
                isPoss = false;
                break;
            }
        }

        vis.put(recipe, isPoss);
        return isPoss;
    }
}
