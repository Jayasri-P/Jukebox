import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Home {
    PreparedStatement ps;
    PreparedStatement ps1;
    Connection c;
    DbConnection cd;

    public Home() {
        cd = new DbConnection();
        c = cd.creatingCon();
    }

    public void page1() {
        try {
            String query1 = "Select songId,songName from song";
            ps = c.prepareStatement(query1);
            ResultSet rs = ps.executeQuery();
            System.out.println();
            System.out.println("List of Songs :");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getString(2));
            }
            System.out.println();
            System.out.println("*********************************************************");
            System.out.println();
            System.out.println("List of Podcasts :");
            String query2 = "Select podId,podName from podcast order by celebId ";
            ps1 = c.prepareStatement(query2);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("");
        }
    }

   public void page2() {
        try {
            System.out.println("List of Podcasts :");
            String query2 = "Select podId,podName from podcast order by celebId ";
            ps = c.prepareStatement(query2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getString(2));
            }
            System.out.println("***********************************************************");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}