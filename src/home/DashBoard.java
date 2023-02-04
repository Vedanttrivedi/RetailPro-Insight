package home;

import authentication.SignUp;
import catelog.Product;
import catelog.ProductOperation;

import java.util.ArrayList;
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
                    if(profile.getItemsBought()==0)
                    {
                        //user has not bought anything!
                        System.out.println("No Products in your bucket ! You should buy something!");

                    }
                    else
                    {
                        //show all of user 's products
                        System.out.println("\t\tName\t\t|Points\t\t");
                        ArrayList<Product> data = profile.getMyBucket();
                        int start = profile.getItemsBought()-1;
                        for(int val=start;val>=0;val--)
                        {
                            System.out.print("\t\t"+data.get(val).getName());
                            System.out.println("\t\t"+data.get(val).getPoints());
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
                    if(finder!=null)
                    {
                        //we find the product in the store now check if it is in stock
                        if(finder.isAvailable()){
                            //now check for the purchase
                            System.out.print("How Many Items You want to purchase : ");
                            int quantity = scan.nextInt();
                            if(quantity > finder.getStock())
                            {
                                System.out.println("Not much stock is available! Buy As much as it is left in stock??");
                                System.out.println("\t press 1) for max stock buy, 2) for no buy");
                                int val = scan.nextInt();
                                if(val==1)
                                    quantity = finder.getStock();
                                else
                                    quantity = 0;
                            }
                            if(quantity==0)
                                break;
                            //check for discount and decide final amount after deducting discount
                            int discountedPrice = finder.getDiscountPercent();

                            if(discountedPrice==0)
                                discountedPrice = finder.getPoints();
                            else
                            {
                                int discount = (finder.getPoints() * finder.getDiscountPercent()) / 100;
                                discountedPrice = (finder.getPoints() - discount)*quantity;
                            }
                            //check if user has enough balance
                            if( profile.getPoints()>= discountedPrice)
                            {
                                int oldItemsBought = profile.getItemsBought();
                                int oldPoints = profile.getPoints();
                                profile.setItemsBought(oldItemsBought+quantity);
                                profile.setPoints(oldPoints - discountedPrice);
                                for(int i=0;i<quantity;i++)
                                    profile.addItem(new Product(finder.getName(),discountedPrice/quantity),oldItemsBought+i);
                                if(quantity==1)
                                    System.out.println("Item is Bought !");
                                else
                                    System.out.println("Items are Bought!");
                                if(finder.getDiscountPercent()!=0){
                                    System.out.println("You are given "+finder.getDiscountPercent()+"% discount! On Total "+quantity+" "+finder.getName());
                                }
                                po.setProductStock(ch,profile,quantity);
                            }
                            else
                            {
                                    System.out.println("you may not have enough balance ");
                            }
                        }
                        else
                        {
                            System.out.println("Product is not in the stock!");
                        }
                    }
                    else
                    {
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

