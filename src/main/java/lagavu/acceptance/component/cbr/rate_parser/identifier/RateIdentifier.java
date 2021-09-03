package lagavu.acceptance.component.cbr.rate_parser.identifier;

public enum RateIdentifier {

    USD("R01235"),
    EUR("R01239"),
    CHF("R01775"),
    GBP("R01035");

    private final String identifier;

    RateIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
