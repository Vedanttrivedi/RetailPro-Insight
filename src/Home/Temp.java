package Home;
import Authentication.SignUp;
import Authentication.SignIn;
import java.util.Scanner;

public class Temp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int MAX_USERS=3;
        boolean isSystemOn = true;
        SignUp register=null;
        SignUp[] users = new SignUp[3];
        int totalUsers=0;
        SignIn login=null;
        while(isSystemOn){
            System.out.println("Home->>Press 1 for Registration,\t 2 for Login,\t 3 for Forget password,\t  0 to exit the application");
            System.out.print("Enter choice :");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("\t\tRegistration Form!!");
                   if(totalUsers >=  MAX_USERS){
                       System.out.println("Max User Registration Limit Reached! You can login in other accounts!");
                   }else{
                       users[totalUsers] = new SignUp(users,totalUsers);
                       totalUsers++;
                   }
                    break;
                case 2:
                    System.out.println("\t\tLogin Form!!");
                    System.out.print("Enter username :");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.print("Enter password :");
                    String pass = sc.nextLine();
                    //check if the user exits in our list of users
                   int index = SignUp.ifUserExits(users,name,totalUsers);
                   if(index!=-1){
                       login = new SignIn();
                       SignUp user = SignUp.getUser(users,index);
                       boolean val = login.doLogin(name,pass,user);
                       if(val){
                           //user is logged in show user dashboard
                           System.out.println("\t\tYou are logged In!");
                           register = user;
                           DashBoard board = new DashBoard(register);

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
                    System.out.println("\t\tPassword Reset Form!!");
                    System.out.print("Enter Username to reset password : ");
                    String tempname = sc.next();
                    //check if the user exits with that username
                    int checker = SignUp.ifUserExits(users,tempname,totalUsers);

                    if(checker!=-1){
                        //user exists so we can change the password
                        System.out.print("Enter new password : ");
                        String pwd = sc.next();
                        users[checker].setPassword(pwd);
                        System.out.println("Your password has been updated! You can login now");
                    }else{
                        //username does not exists
                        System.out.println("Account does not exists with that username! Try again");
                    }
                    break;
                case 0:
                    isSystemOn = false;
                    break;
            }

        }
        System.out.println("System is off!");
    }
}
