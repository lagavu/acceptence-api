package lagavu.acceptance.domain.appeal.entity.value_object;

public enum AppealType {

    CALL(Values.CALL),
    FEEDBACK(Values.FEEDBACK),
    OFFLINE(Values.OFFLINE);

    private String value;

    AppealType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static class Values {
        public static final String CALL = "call";
        public static final String FEEDBACK = "feedback";
        public static final String OFFLINE = "offline";
    }
}
