package lagavu.acceptance.component.cbr.rate_parser.identifier;

public enum RateIdentifier {

    USD("R01235");

    private final String identifier;

    RateIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
