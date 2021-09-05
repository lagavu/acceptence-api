package lagavu.acceptance.domain.appeal.entity.valueObject;

public enum Currency {
    USD("USD"),
    EUR("EUR"),
    CHF("CHF"),
    GBP("GBP");

    private final String currency;

    Currency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
