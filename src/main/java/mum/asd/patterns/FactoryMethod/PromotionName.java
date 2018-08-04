package mum.asd.patterns.FactoryMethod;

public enum PromotionName {
    SpringHoliday("SpringHoliday"),
    FallHoliday("FallHoliday"),
    SummerHoliday("SummerHoliday"),
    WinterHoliday("WinterHoliday");

    private final String text;

    PromotionName(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
