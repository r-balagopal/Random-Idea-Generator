package com.example;

public class Idea {
    private String category;
    private String tags;
    private String description;

    public Idea(String category, String tags, String description) {
        this.category = category;
        this.tags = tags;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public String getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return category + "|" + tags + "|" + description;
    }

    public static Idea fromString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 3) return null;
        return new Idea(parts[0], parts[1], parts[2]);
    }
}
