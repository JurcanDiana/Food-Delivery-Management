package business;

import java.io.Serializable;

/**
 * Abstract Class for Item from the Menu
 */
public abstract class MenuItem implements Serializable {

    private String title;

    public MenuItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract int computePrice();

    public abstract float getRating();

    public abstract int getCalories();

    public abstract int getFat();

    public abstract int getSodium();

    public abstract int getProtein();
}
