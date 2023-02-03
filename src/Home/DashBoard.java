package Home;

import Authentication.SignUp;
import Catelog.Product;
import Catelog.ProductOperation;

import java.util.Scanner;

public class DashBoard {
    //responsible class for all the activities of user when they are logged in!
    private ProductOperation po;
    public DashBoard(SignUp profile,ProductOperation po){
        this.po = po;
        po.showProduct();
        showMenu(profile);
    }
    public void showMenu(SignUp profile){
        Scanner scan = new Scanner(System.in);
        int choice;
        boolean isLoggedIn=true;
        while(isLoggedIn){

            System.out.println("("+profile.getUsername()+")"+"DashBoard->>Press 1 for Show Profile, 2 for Purchase,3 for logout");
            choice = scan.nextInt();
            switch (choice){
                case 1:
                    //show profile
                    System.out.print("Full Name :"+profile.getFullname());
                    System.out.print("\tUsername :"+profile.getUsername());
                    System.out.print("\tPoints :"+profile.getPoints());
                    System.out.print("\tCart :"+profile.getItemsBought());
                    System.out.println("\nMy Products");
                    if(profile.getItemsBought()==0){
                        //user has not bought anything!
                        System.out.println("No Products in your bucket ! You should buy something!");

                    }else{
                        //show all of user 's products
                        System.out.println("\t\tName\t\t|Points\t\t");
                        Product[]data = profile.getMyBucket();
                        int start = profile.getItemsBought()-1;
                        for(int val=start;val>=0;val--){
                            System.out.print("\t\t"+data[val].getName());
                            System.out.println("\t\t"+data[val].getPoints());
                        }
                    }
                    System.out.println("("+profile.getUsername()+")"+"Profile->>Press 1 for back to cateloge, 2 for logout");
                    int ans = scan.nextInt();
                    if(ans==2){
                        System.out.println("You are logout!");
                        isLoggedIn=false;
                    }else{
                        po.showProduct();
                    }
                    break;
                case 2:
                    //purchase related options
                    po.showProduct();
                    System.out.print("Enter Item you want to buy :");

                    String mychoice = scan.nextLine();
                    if(mychoice.length()==0)
                        mychoice = scan.nextLine();
                    //search for the product in store

                    int ch = po.findProduct(mychoice);
                    Product finder;
                    if(ch!=-1)
                        finder =  po.getProduct(ch);
                    else
                        finder = null;
                    if(finder!=null){
                        //we find the product in the store now check if it is in stock
                        if(finder.isAvailable()){
                            //now check for the purchase
                            boolean isit = false;
                            if(profile.getItemsBought() >= 3){
                                System.out.println("your Cart is full!You cannot purchase");
                                isit=true;
                            }
                            //check for discount and decide final amount after deducting discount
                            int discountedPrice = finder.getDiscountPercent();
                            if(discountedPrice==0)
                                discountedPrice = finder.getPoints();
                            else{
                                int discount = (finder.getPoints() * finder.getDiscountPercent()) / 100;
                                discountedPrice = finder.getPoints() - discount;
                            }
                            //check if user has enough balance
                            if(!isit && profile.getPoints()>= discountedPrice){
                                int oldItemsBought = profile.getItemsBought();
                                int oldPoints = profile.getPoints();
                                profile.setItemsBought(oldItemsBought+1);
                                profile.setPoints(oldPoints - discountedPrice);
                                profile.addItem(new Product(finder.getName(),discountedPrice),oldItemsBought);
                                System.out.println("Item is Bought !");
                                if(finder.getDiscountPercent()!=0){
                                    System.out.println("You are given "+finder.getDiscountPercent());
                                }
                                po.setProductStock(ch);
                            }else{
                                if(!isit)
                                    System.out.println("you may not have enough balance ");
                            }
                        }else{
                            System.out.println("Product is not in the stock!");
                        }
                    }else{
                        System.out.println("Product does not found in store");
                    }
                    break;
                case 3:
                    System.out.println("You are logout!");
                    isLoggedIn=false;
                    break;
            }
        }

    }
}
