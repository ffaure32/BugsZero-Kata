package com.adaptionsoft.games.uglytrivia;

public enum Category {
    POP("Pop"), SCIENCE("Science"), SPORTS("Sports"), ROCK("Rock");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    static Category get(int position) {
        Category[] categories = values();
        return categories[position % categories.length];
    }

    @Override
    public String toString() {
        return name;
    }
}
