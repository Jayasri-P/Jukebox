import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class UserLogin {
    Scanner sc = new Scanner(System.in);
    PreparedStatement ps;
    Connection c;
    DbConnection cd;

    public UserLogin() {
        cd = new DbConnection();
        c = cd.creatingCon();
    }

    public void option() {

        System.out.println("Please SignUp/Login before you create your own playlist");
        System.out.println("Select \n 1.signUp\n 2.Login");
        int temp = sc.nextInt();
        if (temp == 1) {
            signUp();
        } else if (temp == 2) {
            login();
        }
    }

    public void signUp() {
        try {
            int Id = (int) new Random().nextInt(100000);
            System.out.println("Enter your name");
            String name = sc.next();
            System.out.println("Enter your phone No");
            String phn = sc.next();

            System.out.println("Enter your email-id");
            String email = sc.next();
            System.out.println("Enter your password");
            String pw = sc.next();
            ps = c.prepareStatement("insert into user values (?,?,?,?,?);");
            ps.setInt(1, Id);
            ps.setString(2, name);
            ps.setString(3, phn);
            ps.setString(4, email);
            ps.setString(5, pw);
            int res = ps.executeUpdate();
            MainImplementation.login_status = "loggedIn";
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void login() {
        try {
            System.out.println("Enter your loginId");
            String s1 = sc.next();
            System.out.println("Enter your password");
            String s2 = sc.next();
            String query = "Select * from user where phoneNo=? and password=?";
            ps = c.prepareStatement(query);
            ps.setString(1, s1);
            ps.setString(2, s2);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                MainImplementation.login_status = "loggedIn";
            } else {
                System.out.println("Invalid user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}