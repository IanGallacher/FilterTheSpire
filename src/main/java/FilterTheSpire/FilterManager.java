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

    public static void setFirstCombatIs(String enemyName) {
        NthCombatFilter filter = new NthCombatFilter(enemyName);
        filters.add(filter);
    }

//    public static void setFirstCombatsAre(ArrayList<String> enemyNames) {
//        ArrayList<String> combatOrder = enemyNames;
//        NthCombatFilter filter = new NthCombatFilter(combatOrder);
//        filters.add(filter);
//    }

    // --------------------------------------------------------------------------------

    public static void setBossSwapIs(String relic) {
        NthBossRelicFilter filter = new NthBossRelicFilter(relic);
        filters.add(filter);
    }

    public static void setBossSwapFiltersFromValidList(ArrayList<String> relicIDs) {
        ArrayList<AbstractFilter> filtersToCheck = new ArrayList<>();
        for (String relicID : relicIDs) {
            NthBossRelicFilter filter = new NthBossRelicFilter(relicID);
            filtersToCheck.add(filter);
        }
        setORValidator(filtersToCheck);
    }

    // --------------------------------------------------------------------------------

    public static void setFirstBossIs(String bossName) {
        BossFilter filter = new BossFilter(bossName);
        filters.add(filter);
    }

    public static void setFirstBossIsOneOf(ArrayList<String> bossNames) {
        ArrayList<AbstractFilter> filtersToCheck = new ArrayList<>();
        for (String bossName : bossNames) {
            BossFilter filter = new BossFilter(bossName);
            filtersToCheck.add(filter);
        }
        setORValidator(filtersToCheck);
    }

    // --------------------------------------------------------------------------------

    public static void setFirstEliteIs(String eliteName) {
        NthEliteFilter filter = new NthEliteFilter(eliteName);
        filters.add(filter);
    }

    public static void setFirstEliteIsOneOf(ArrayList<String> eliteNames) {
        ArrayList<AbstractFilter> filtersToCheck = new ArrayList<>();
        for (String eliteName : eliteNames) {
            NthEliteFilter filter = new NthEliteFilter(eliteName);
            filtersToCheck.add(filter);
        }
        setORValidator(filtersToCheck);
    }

    // --------------------------------------------------------------------------------

    public static void print() {
        System.out.println("FilterManager has " + filters.size() + " filters");
    }
}
