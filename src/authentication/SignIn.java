package authentication;


public class SignIn {
    public boolean doLogin(String username,String password,SignUp sp){
        return sp!=null && username.equals(sp.getUsername()) && password.equals(sp.getPassword());
    }

}
