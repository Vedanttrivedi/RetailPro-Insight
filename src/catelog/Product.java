package catelog;


public class Product {;
    public void setName(String name) {
        this.name = name;
    }
    private String name;
    private boolean isAvailable;


    private int points;
    private int stock;
    private int discountPercent;

    public int getDiscountPercent() {

        return discountPercent;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }


    public Product(String name,boolean isAvailable,int points,int stock,int discount){
        this.name = name;
        this.isAvailable = isAvailable;
        this.points = points;
        this.stock = stock;
        this.discountPercent = discount;
    }
    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getPoints() {
        return points;
    }

    public int getStock() {
        return stock;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
