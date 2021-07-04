package net.bugfixers.doodleinc.model;

public class SelectedItem {

    private String name;
    private int category;
    private int subcategory;

    public SelectedItem(String name, int category, int subcategory) {
        this.name = name;
        this.category = category;
        this.subcategory = subcategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }
}
