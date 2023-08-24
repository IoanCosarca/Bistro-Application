package com.ntt.bistroapplication.domain;

public enum IngredientType {
    FLOUR("FLOUR"),
    EGGS("EGGS"),
    MILK("MILK"),
    SUGAR("SUGAR"),
    SUN_OIL("SUNFLOWER OIL"),
    SALT("SALT"),
    BUTTER("BUTTER"),
    YEAST("YEAST"),
    PASTA("PASTA"),
    TOMATO_SAUCE("TOMATO SAUCE"),
    MEATBALLS("MEATBALLS"),
    GARLIC("GARLIC"),
    CHEESE("CHEESE"),
    BACON("BACON"),
    MUSHROOMS("MUSHROOMS"),
    PEPPERS("PEPPERS"),
    CORN("CORN"),
    RICE("RICE"),
    ONION("ONION"),
    CELERY("CELERY"),
    BASIL("BASIL"),
    CHOCOLATE("CHOCOLATE CREAM"),
    STRAWBERRY("STRAWBERRY JAM"),
    PEACHES("PEACHES JAM"),
    RASPBERRY("RASPBERRIES JAM");
    private final String name;

    IngredientType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
