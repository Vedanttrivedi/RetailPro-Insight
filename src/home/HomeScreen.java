package home;
import authentication.SignUp;
import authentication.SignIn;
import catelog.ProductOperation;
import java.util.HashMap;
import java.util.Scanner;
import java.io.Console;
public class HomeScreen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Console cls = System.console();
        ProductOperation po = new ProductOperation();
        boolean isSystemOn = true;
        SignUp register=null;
        HashMap<String,SignUp> users = new HashMap<>();
        int totalUsers=0;
        SignIn login=null;
        while(isSystemOn)
        {
            System.out.println("Home->>Press 1 for Registration,\t 2 for Login,\t 3 for Forget password,\t  0 to exit the application");
            System.out.print("Enter choice :");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("\t\tRegistration Form!!");
                    System.out.print("Enter Username :");
                    String tempUsername = sc.next();

                    //check if user exists in hashmap of users
                    if(users.containsKey(tempUsername))
                    {
                        System.out.println("Username already exists!cannot create account!");
                    }
                    else
                    {
                        //username does not exist . so we can create account
                        SignUp user = new SignUp(tempUsername);
                        users.put(tempUsername,user);
                        totalUsers++;
                        System.out.println("SignUp is successful! You can login now");
                    }
                    break;
                case 2:
                    System.out.println("\t\tLogin Form!!");
                    System.out.print("Enter username :");
                    sc.nextLine();
                    String name = sc.nextLine();
                    //check if the user exits in our list of users
                    SignUp tempLoggedUser = users.get(name);
                    if(tempLoggedUser!=null)
                    {
                        //username exists to we can ask for  password
                        System.out.print("Enter password :");
                        String pwd;
                        if(cls!=null)
                        {
                            char[] tempPass = cls.readPassword("");
                            pwd = String.valueOf(tempPass);
                            if(pwd.length()!=0)
                            {
                                for(char cd:tempPass)
                                    System.out.print("*");
                            }
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("System.console is null. using scanner to input password");
                            pwd = sc.next();
                        }
                        login = new SignIn();
                        //now check for password matching
                        boolean checkForPassword = login.doLogin(name,pwd,tempLoggedUser);
                        if(checkForPassword){
                            //user is logged in show user dashboard
                            System.out.println("\t\tYou are logged In!");
                            register = users.get(name);
                            DashBoard board = new DashBoard(register,po);
                            //Now user is logged out from dashboard so  set login and register as  null
                            register = null;
                            login=null;
                        }else{
                            System.out.println("Invalid Details");
                        }
                    }else {
                        System.out.println("Account Not Found!");
                    }
                    break;

                case 3:
                    SignUp temp = new SignUp();
                    temp.forgetPassword(users);
                    break;
                case 0:
                    isSystemOn = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
        System.out.println("System is off!");
    }
}