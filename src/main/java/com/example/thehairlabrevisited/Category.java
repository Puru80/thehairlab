package com.example.thehairlabrevisited;

public enum Category {
    HAIR("HAIR*"),
    COLOUR_TEXTURE("COLOUR & TEXTURE*"),
    LOREAL_HAIR_TREATMENTS("LOREAL HAIR TREATMENTS*"),
    LOTUS_FACIALS("LOTUS FACIALS"),
    NAIL_SERVICES("NAIL SERVICES*"),
    THREADING("THREADING"),
    BLEACH_SKIN_POLISH("BLEACH & SKIN POLISH*"),
    WAXING("WAXING"),
    BODY_ESSENTIALS("BODY ESSENTIALS*");

    private final String name;

    Category(String s) {
        name = s;
    }

    public static String getName(Category category){
        return category.name;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
