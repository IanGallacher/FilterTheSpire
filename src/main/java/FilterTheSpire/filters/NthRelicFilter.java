package FilterTheSpire.filters;

import FilterTheSpire.simulators.RelicRngSimulator;

// This is not a "true" relic filter, it grabs the nth index of all relic pool rarities and checks for a match.
// It does not guarantee that Nth relic that you actually receive matches.
public class NthRelicFilter extends AbstractFilter{
    private String relicName;
    private int encounterIndex;

    public NthRelicFilter(String relicName) {
        this.relicName = relicName;
        this.encounterIndex = 0; // Get the first elite encounter if no index is specified.
    }

    public NthRelicFilter(String relicName, int encounterIndex) {
        this.relicName = relicName;
        this.encounterIndex = encounterIndex;
    }

    public boolean isSeedValid(long seed) {
        RelicRngSimulator relicSim = new RelicRngSimulator(seed);

        if (relicSim.nthCommonRelic(encounterIndex).equals(relicName)) {
            return true;
        }

        if (relicSim.nthUncommonRelic(encounterIndex).equals(relicName)) {
            return true;
        }

        if (relicSim.nthRareRelic(encounterIndex).equals(relicName)) {
            return true;
        }

        return false;
    }
}
