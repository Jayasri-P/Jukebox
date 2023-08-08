import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class NewPlayList {
    Scanner sc = new Scanner(System.in);
    PreparedStatement ps;
    Connection c;
    DbConnection cd;

    public NewPlayList() {
        cd = new DbConnection();
        c = cd.creatingCon();
    }

    public void create_playList() throws SQLException {
        System.out.println("Enter your choice\n 1.songs\n 2.podcasts");
        int x = sc.nextInt();
        if (x == 1) {
            int pId = (int) new Random().nextInt(100000);
            System.out.println("Enter the playList name");
            String name = sc.next();
            System.out.println("------------------------------------------------------");
            Home home = new Home();
            home.page1();
            int a = 0;
            try {
                do {
                    System.out.println("Enter the song id you wish to add to your playlist");
                    int s = sc.nextInt();
                    String query = "insert into playlistSongs values (?,?,?)";
                    ps = c.prepareStatement(query);
                    ps.setInt(1, pId);
                    ps.setInt(2, s);
                    ps.setString(3, name);
                    int res = ps.executeUpdate();
                    System.out.println("Do you wish to add another song? 1.Yes\n 2.No");
                    int x1 = sc.nextInt();
                    if (x1 == 1) {
                        a = 1;
                    } else {
                        a = 0;
                    }
                } while (a == 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (x == 2) {
            int podId = (int) new Random().nextInt(100000);
            System.out.println("Enter the playList name you already created");
            String name = sc.next();
            Home home = new Home();
            home.page2();
            int a = 0;
            try {
                do {
                    System.out.println("Enter the podcast id you wish to add to your playlist");
                    int s = sc.nextInt();
                    String query = "insert into playlistPodcasts values (?,?,?);";
                    ps = c.prepareStatement(query);
                    ps.setInt(1, podId);
                    ps.setInt(2, s);
                    ps.setString(3, name);
                    int res = ps.executeUpdate();
                    System.out.println("Do you wish to add another podcast? 1.Yes\n 2.No");
                    int x1 = sc.nextInt();
                    if (x1 == 1) {
                        a = 1;
                    } else {
                        a = 0;
                    }
                } while (a == 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}