package Catelog;


public class ProductOperation {
    public Product[] products;

    public ProductOperation(){
        products  = new Product[5];
        products[0] = new Product("Redmi 10A",true,10000,3,10);
        products[1] = new Product("MacBook Pro",true,300000,2,20);
        products[2] = new Product("Smart Tv",false,80000,0,5);
        products[3] = new Product("boAT Airpod",true,2000,5,0);
        products[4] = new Product("Iphone 13",true,90000,4,5);
        showProduct();
    }

    public void showProduct(){

        System.out.println("List Of Products in Store");
        //show all products
        System.out.println("Name\t\t|Points\t\t|Availability|\tStock\t|Discount(%)");
        for(Product product: products){
            System.out.println(product.getName()+"\t\t|"+product.getPoints()+"\t\t|"+product.isAvailable()+"\t\t|"+product.getStock()+"\t\t|"+product.getDiscountPercent());
        }
        System.out.println();
    }

    public int findProduct(String name){
        for(int val=0;val<products.length;val++){
            if(name.equals(products[val].getName())){
                return val;
            }
        }
        return -1;
    }
    public Product getProduct(int index){
        if(index < 0 && index > 4)
            return null;
        return products[index];
    }
    public void setProductStock(int index){
        if(index < 0 && index > 4)
            return;
        int oldstock = products[index].getStock();
        products[index].setStock(oldstock-1);
        if(oldstock-1 ==0)
            products[index].setAvailable(false);
    }

}
