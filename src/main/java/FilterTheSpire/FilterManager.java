package FilterTheSpire;

import FilterTheSpire.filters.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@SpireInitializer
public class FilterManager {
    // Singleton pattern
    private static class FilterManagerHolder { private static final FilterManager INSTANCE = new FilterManager(); }
    private static FilterManager getInstance() { return FilterManagerHolder.INSTANCE; }
    public static void initialize() { getInstance(); }

    private static ArrayList<AbstractFilter> filters = new ArrayList<>();

    // Returns true if all filters pass for the given seed
    public static boolean validateFilters(long seed) {
        return filters.stream().allMatch(v -> v.isSeedValid(seed));
    }

    public static boolean hasFilters() {
        return filters.size() > 0;
    }

    // --------------------------------------------------------------------------------

    public static void setORValidator(ArrayList<AbstractFilter> filtersToMatch) {
        OrFilter filter = new OrFilter(filtersToMatch);
        filters.add(filter);
    }

    public static void AddFilter(AbstractFilter filter) {
        filters.add(filter);
    }

    // --------------------------------------------------------------------------------

    public static void setBossSwapFiltersFromValidList(ArrayList<String> relicIDs) {
        ArrayList<AbstractFilter> filtersToCheck = new ArrayList<>();
        for (String relicID : relicIDs) {
            NthBossRelicFilter filter = new NthBossRelicFilter(relicID);
            filtersToCheck.add(filter);
        }
        setORValidator(filtersToCheck);
    }

    // --------------------------------------------------------------------------------

    public static void print() {
        System.out.println("FilterManager has " + filters.size() + " filters");
    }
}
