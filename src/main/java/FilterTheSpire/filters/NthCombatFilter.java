package FilterTheSpire.filters;

import FilterTheSpire.simulators.MonsterRngSimulator;

public class NthCombatFilter extends AbstractFilter {
    private String enemyName;
    private int encounterIndex;

    public NthCombatFilter(String enemyName) {
        this.enemyName = enemyName;
        this.encounterIndex = 0; // Get the first combat if no index is specified.
    }

    public NthCombatFilter(String enemyName, int encounterIndex) {
        this.enemyName = enemyName;
        this.encounterIndex = encounterIndex;
    }

    public boolean isSeedValid(long seed) {
        return new MonsterRngSimulator(seed).nthCombat(encounterIndex).equals(enemyName);
    }
}
