package FilterTheSpire.filters;

import FilterTheSpire.simulators.CardRngSimulator;

public class NthColorlessRareCardFilter extends AbstractFilter{
    private String cardName;
    private int encounterIndex;

    public NthColorlessRareCardFilter(String cardName) {
        this.cardName = cardName;
        this.encounterIndex = 1; // Get the first card if no index is specified.
    }

    public NthColorlessRareCardFilter(String cardName, int encounterIndex) {
        this.cardName = cardName;
        this.encounterIndex = encounterIndex;
    }

    public boolean isSeedValid(long seed) {
        return new CardRngSimulator(seed).nthColorlessRareCard(encounterIndex).equals(cardName);
    }
}
