package business;

import java.io.Serializable;
import java.util.*;

/**
 * Composite Product Class
 */
public class CompositeProduct extends MenuItem implements Serializable {

    private List<MenuItem> menuItemList;    //list of composite products
    private int price;                      //price of composite product
    
    public CompositeProduct(String title) {
        super(title);
        this.menuItemList = new ArrayList<MenuItem>();
        this.price = 0;
    }

    public String getTitle() {
        return super.getTitle();
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Adding a product to composite
     * @param item product to add
     */
    public void addProduct(MenuItem item) {
        this.menuItemList.add(item);
        int p = computePrice();
        setPrice(p);
    }

    /**
     * Removing an item from composite
     * @param item product to remove
     */
    public void removeProduct(MenuItem item) {
        this.menuItemList.remove(item);
    }

    /**
     * Showing the base products details
     */
    public void showBaseProductsDetail() {
        for (MenuItem item : this.menuItemList) {
            ((BaseProduct)item).showProductDetails();
        }
    }

    /**
     * Computing value for ratings
     * @return average of ratings of all products from composite
     */
    @Override
    public float getRating() {
        float rating = 0;
        for (MenuItem item : this.menuItemList) {
            rating += item.getRating();
        }
        rating /= this.menuItemList.size();
        return rating;
    }

    /**
     * Computing value for calories
     * @return sum of calories quantity of all products from composite
     */
    @Override
    public int getCalories() {
        int calories = 0;
        for (MenuItem item : this.menuItemList) {
            calories += item.getCalories();
        }
        return calories;
    }

    /**
     * Computing value for protein
     * @return sum of protein quantity of all products from composite
     */
    @Override
    public int getProtein() {
        int proteins = 0;
        for (MenuItem item : this.menuItemList) {
            proteins += item.getProtein();
        }
        return proteins;
    }

    /**
     * Computing value for fat
     * @return sum of fat quantity of all products from composite
     */
    @Override
    public int getFat() {
        int fat = 0;
        for (MenuItem item : this.menuItemList) {
            fat += item.getFat();
        }
        return fat;
    }

    /**
     * Computing value for sodium
     * @return sum of sodium quantity of all products from composite
     */
    @Override
    public int getSodium() {
        int sodium = 0;
        for (MenuItem item : this.menuItemList) {
            sodium += item.getSodium();
        }
        return sodium;
    }

    /**
     * Searching a product by name
     * @param name name of the product
     * @return searched product
     */
    public MenuItem searchByName(String name) {
        for (MenuItem item : this.menuItemList) {
            if (item instanceof BaseProduct) {
                if (((BaseProduct)item).getTitle().equals(name)) {
                    return item;
                }
            } else {
                if (((CompositeProduct)item).getTitle().equals(name)) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * Overriding equals() by title
     * @param o object used to compare with
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompositeProduct that = (CompositeProduct) o;
        return super.getTitle().equals(that.getTitle());
    }

    /**
     * Overriding hashCode()
     * @return hashCode() by title
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    /**
     * Computing of the price of composite
     * @return sum of products that compose the final composite product
     */
    @Override
    public int computePrice() {
        int p = 0;
        for (MenuItem item : this.menuItemList) {
            p += item.computePrice();
        }
        this.setPrice(p);
        return p;
    }

    /**
     * Overriding toString()
     * @return string with info regarding composite product
     */
    @Override
    public String toString() {
        return "CompositeProduct {" +
                "title='" + getTitle() + '\'' +
                ", menuItemList=" + menuItemList +
                ", price=" + price +
                "}\n";
    }
}
