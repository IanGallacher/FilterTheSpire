package FilterTheSpire.filters;


// Take a collection of filters and return true if any one is valid.
public class ExcludeFilter extends AbstractFilter {
    private AbstractFilter filter;

    public ExcludeFilter(AbstractFilter filter) {
        this.filter = filter;
    }

    public boolean isSeedValid(long seed) {
        return !filter.isSeedValid(seed);
    }
}
