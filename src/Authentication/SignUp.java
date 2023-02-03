package Authentication;
import java.util.Scanner;
import Catelog.Product;
public class SignUp {
    private String username;

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    private String fullname;
    private int points;
    private int itemsBought;
    private Product[] myBucket;

    public int getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(int itemsBought) {
        this.itemsBought = itemsBought;
    }
    public SignUp(){
        //this.registerUser();
    }
    public SignUp(SignUp[] users,int totalUsers){
        this.registerUser(users,totalUsers);
    }
    public String getName(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getFullname(){
        return this.fullname;
    }
    public void registerUser(){
        //for  single user
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Username :");
        String tempUsername = scan.next();

        this.username = tempUsername;
            System.out.print("Enter fullname :");
            this.fullname = scan.nextLine();
            if(this.fullname.length()==0)
                this.fullname = scan.nextLine();
            System.out.print("Enter password : ");
            this.password = scan.next();
            if(this.password.length()==0)
            {
                System.out.println("Reenter password");
                this.password = scan.next();
            }

            myBucket = new Product[3];
            itemsBought = 0;
            myBucket[0] = new Product();
            myBucket[1] = new Product();
            myBucket[2] = new Product();
            points = 500000;
            System.out.println("SignUp is successful! You can login now");

    }
    public void registerUser(SignUp[] users,int totalUsers){
        //for multiple user

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Username :");
        String tempUsername = scan.next();
        int valid = ifUserExits(users,tempUsername,totalUsers);
        if(valid==1){
            System.out.println("Username Already exits!Cannot Create Account!");
        }else{
            this.username = tempUsername;

            System.out.print("Enter fullname :");
            this.fullname = scan.nextLine();
            if(this.fullname.length()==0)
                this.fullname = scan.nextLine();

            System.out.print("Enter password : ");
            this.password = scan.next();
            if(this.password.length()==0)
                this.password = scan.next();

            myBucket = new Product[3];
            itemsBought = 0;
            myBucket[0] = new Product();
            myBucket[1] = new Product();
            myBucket[2] = new Product();
            points = 500000;
            System.out.println("SignUp is successful! You can login now");
        }
    }
    public void forgetPassword(String username,SignIn login){
        Scanner sc = new Scanner(System.in);
        if(login!=null && this.username.equals(username)){
            System.out.println("Enter new password : ");
            this.password = sc.nextLine();
            System.out.println("Password is reset ! You can login now");
        }else{
            System.out.println("Username Not Found! try again");
        }

    }
    public void showProfile(){
        System.out.println("Welcome To "+this.fullname+" 's Profile");
        System.out.println("Your Username : "+this.username);
    }
    public String getUsername(){
        return this.username;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addItem(Product product,int index){

        myBucket[index].setName(product.getName());
        myBucket[index].setPoints(product.getPoints());
    }

    public static int ifUserExits(SignUp[] users,String name,int count){
        //find the user with username in users array
        //count indicates how many users are currently registered in our users array
        if(count==0)
            return -1;
        for(int val=0;val<count;val++){

            if(users[val]!=null && users[val].getUsername().equals(name))
                return val;
        }
        return -1;
    }
    public int getPoints() {
        return points;
    }

    public Product[] getMyBucket() {
        return myBucket;
    }
    public static SignUp getUser(SignUp[] users,int index){
        //get the user object from signup users array using index
        if(index < 0 && index >=3)
            return null;
        return users[index];
    }

}
