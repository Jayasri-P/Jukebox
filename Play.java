import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Play {

    Scanner sc = new Scanner(System.in);
    Connection con;
    PreparedStatement ps;
    String query = null;
    int temp = 0;
    DbConnection cd;
    ResultSet rs;
    String path = null;

    public Play() {
        cd = new DbConnection();
        con = cd.creatingCon();
    }
    Home home=new Home();
    public void option() throws SQLException {
        System.out.println("Choose an option you want to listen\n 1.songs\n 2.podcast\n");
        int temp = sc.nextInt();
        if (temp == 1) {
            home.page1();
            view_songs();
        } else if (temp == 2) {
            home.page2();
            view_podcast();
        }
    }

    public void view_songs() {
        try {
            System.out.println("Select\n 1.Play songs list\n 2.Search by artist,album,genre");
            int x = sc.nextInt();
            if (x == 2) {
                System.out.println("Select the option by which you want to search");
                System.out.println("1.Genre\n2.Artist\n3.Album");
                int choice = sc.nextInt();
                {
                    switch (choice) {
                        case 1:
                            String query1="select genreId,genreName from genre";
                            ps=con.prepareStatement(query1);
                            ResultSet rs=ps.executeQuery();
                            System.out.println("Genre list");
                            while (rs.next()){
                                System.out.println(rs.getInt(1)+" "+rs.getString(2));
                            }
                            System.out.println("Enter Genre id");
                            temp = sc.nextInt();
                            query = "select songId,songName from song where genreId=?";
                            break;
                        case 2:
                            query1="select artistId,artistName from artist";
                            ps=con.prepareStatement(query1);
                            rs=ps.executeQuery();
                            System.out.println("Artist list");
                            while (rs.next()){
                                System.out.println(rs.getInt(1)+" "+rs.getString(2));
                            }
                            System.out.println("Enter Artist id");
                            temp = sc.nextInt();
                            query = "select songId,songName from song where artistId=?";
                            break;
                        case 3:
                            query1="select albumId,albumName from album";
                            ps=con.prepareStatement(query1);
                            rs=ps.executeQuery();
                            System.out.println("Album list");
                            while (rs.next()){
                                System.out.println(rs.getInt(1)+" "+rs.getString(2));
                            }
                            System.out.println("Enter Album id");
                            temp = sc.nextInt();
                            query = "select songId,songName from song where albumId=?";
                            break;
                    }
                }
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, temp);
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "    " + rs.getString(2));
                }
            }
            String query1 = "Select filePath from song where songId=?";
            System.out.println("Enter the songId");
            int res = sc.nextInt();
            ps = con.prepareStatement(query1);
            ps.setInt(1, res);
            rs = ps.executeQuery();
            while (rs.next()) {
                path = rs.getString(1);
            }
            AudioPlayer.operate(path);
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void view_podcast() {
        try {
            System.out.println("Select\n 1.Listen to podcasts\n 2.Search by podcastId,date");
            int x = sc.nextInt();
            if (x == 2) {
                System.out.println("Select the filter by which you want to search");
                System.out.println("1.podcastId\n 2.date");
                int choice = sc.nextInt();
                {
                    switch (choice) {
                        case 1:
                            System.out.println("Enter artist id");
                            temp = sc.nextInt();
                            query = "select * from podcast where celebId=?";
                            break;
                        case 2:
                            System.out.println("Enter publishingdate");
                            temp = sc.nextInt();
                            query = "select * from podcast where podDop=?";
                            break;
                    }
                }
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, temp);
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "    " + rs.getString(2));
                }
            }
            String query = "Select filePath from podcast where podId=?";
            System.out.println("enter the podcastId");
            int res = sc.nextInt();
            PreparedStatement ps1 = con.prepareStatement(query);
            ps1.setInt(1, res);
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                path = rs.getString(1);
            }
            AudioPlayer.operate(path);
        } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
