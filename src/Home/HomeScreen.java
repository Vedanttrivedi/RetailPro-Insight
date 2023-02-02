package Home;
import Authentication.SignUp;
import Authentication.SignIn;
import java.util.Scanner;

public class HomeScreen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                    register =new SignUp();
                    break;
                case 2:
                    System.out.print("Enter username :");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.print("Enter password :");
                    String pass = sc.nextLine();

                    if(register!=null){

                        login = new SignIn();
                        boolean val = login.doLogin(name,pass,register);
                        if(val){
                            //user is logged in show user dashboard
                            System.out.println("\t\tYou are logged In!");
                            DashBoard board = new DashBoard(register);
                            register=null;
                            login=null;
                        }else {
                            System.out.println("Invalid Credential!!");
                        }
                    }else{
                        System.out.println("Invalid Credential");
                    }
                    break;
                case 3:
                    System.out.println("Enter Username to reset password");
                    String tempname = sc.next();
                     if(register !=null ){
                        register.forgetPassword(tempname,login);
                    }else{
                        System.out.println("You have to create account first!");
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
