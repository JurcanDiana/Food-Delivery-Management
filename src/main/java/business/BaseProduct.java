package business;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base product Class
 */
public class BaseProduct extends MenuItem implements Serializable {

    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public BaseProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        super(title);
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    /**
     * Getters
     */
    public String getTitle() {
        return super.getTitle();
    }

    public float getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public int getPrice() {
        return price;
    }

    /**
     * Setters
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Show details of the product
     */
    public void showProductDetails() {
        System.out.println("Title: " + getTitle()
                + " rating: " + rating
                + " calories: " + calories
                + " protein: " + protein
                + " fat: " + fat
                + " sodium: " + sodium
                + " price: " + price);
    }

    /**
     * The computing of the price
     * @return price of product
     */
    @Override
    public int computePrice() {
        return this.price;
    }

    /**
     * Overriding of the method equals() for the name of the product
     * @param o object which we use to compare
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)  {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseProduct that = (BaseProduct) o;
        return getTitle().equals(that.getTitle());
    }

    /**
     * Overriding hashCode()
     * @return hasCode by the title
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    /**
     * Overriding toString()
     * @return string with info regarding each base product
     */
    @Override
    public String toString() {
        return "BaseProduct {" +
                "title='" + getTitle() + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                "}\n";
    }
}
