package net.bugfixers.doodleinc.model;

import java.util.ArrayList;

public class Category {

    private int category_id;
    private String category_name;
    private ArrayList<Subcategory> subcatg;
    private boolean expanded;
    private boolean selected;

    public Category() {

    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public ArrayList<Subcategory> getSubcatg() {
        return subcatg;
    }

    public void setSubcatg(ArrayList<Subcategory> subcatg) {
        this.subcatg = subcatg;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
