package lagavu.acceptance.domain.appeal.entity.value_object;

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
