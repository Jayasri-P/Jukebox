import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    Connection con;

    public Connection creatingCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/PlayMusic";
            con = DriverManager.getConnection(url, "root", "root@123");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println(c.getMessage());
        }
        return con;
    }
}
