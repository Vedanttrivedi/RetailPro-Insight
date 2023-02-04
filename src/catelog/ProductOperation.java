package catelog;


import authentication.SignUp;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductOperation {
    private ArrayList<Product> products;

    public ProductOperation(){
        products = new ArrayList<>();
        products.add(new Product("Redmi 10A",true,10000,6,10));
        products.add(new Product("MacBook Pro",true,300000,5,20));
        products.add(new Product("Smart Tv",false,80000,0,5));
        products.add(new Product("boAT Airpod",true,2000,5,0));
        products.add(new Product("Iphone 13",true,90000,4,5));

    }

    public void showProduct(){

        System.out.println("List Of Products in Store");
        //show all products
        System.out.println("Name\t\t\t\t|Points\t\t|Availability|\tStock\t|Discount(%)");
        for(Product product: products){
            System.out.println(product.getName()+"\t\t|"+product.getPoints()+"\t\t|"+product.isAvailable()+"\t\t|"+product.getStock()+"\t\t|"+product.getDiscountPercent());
        }
        System.out.println();
    }

    public int findProduct(String name){
        for(int val=0;val<products.size();val++){
            if(name.equals(products.get(val).getName())){
                return val;
            }
        }
        return -1;
    }
    public Product getProduct(int index){
        if(index < 0)
            return null;
        return products.get(index);
    }
    public void setProductStock(int index, SignUp user,int quantity){
        if(index < 0)
            return;
        int oldstock = products.get(index).getStock();
        products.get(index).setStock(oldstock-quantity);
        if(oldstock-quantity ==0)
            products.get(index).setAvailable(false);
    }

}
