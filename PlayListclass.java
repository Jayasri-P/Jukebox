import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PlayListclass {
    Scanner sc = new Scanner(System.in);
    PreparedStatement ps;
    Connection c;
    DbConnection cd;

    public PlayListclass() {
        cd = new DbConnection();
        c = cd.creatingCon();
    }

    public void play_playlist() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        System.out.println("Enter the option you want to view\n 1.songs\n 2.podcast");
        int x = sc.nextInt();
        if (x == 1) {
            try {
                String query1 = "Select distinct pListSong,pListName from playlistSongs;";
                ps = c.prepareStatement(query1);
                ResultSet rs = ps.executeQuery();
                System.out.println("List of songs present in playlist");
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " " + rs.getString(2));
                }
                String query = "Select filepath from song where songId in(select songId from playlistSongs where pListSong=?) order by songId desc";
                ps = c.prepareStatement(query);
                System.out.println("Enter the playlist id");
                int id = sc.nextInt();
                ps.setInt(1, id);
                rs = ps.executeQuery();
                int res = 0;

                List<String> songlinks = new LinkedList<>();
                while (rs.next() && res == 0) {
                    songlinks.add(rs.getString(1));
                }
                songlinks.stream().forEach((song) -> {
                    try {
                        AudioPlayer.operate(song);
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (x == 2) {
            try {
                String query1 = "Select distinct pListPod,podName from playlistPodcasts";
                ps = c.prepareStatement(query1);
                ResultSet rs = ps.executeQuery();
                System.out.println("List of podcasts present in playlist");
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " " + rs.getString(2));
                }
                String query = "Select * from  playlistPodcasts where pListPod=res";
                ps = c.prepareStatement(query);
                rs = ps.executeQuery();

                List<String> podcastlinks = new LinkedList<>();
                while (rs.next()) {
                    podcastlinks.add(rs.getString(1));
                }
                podcastlinks.stream().forEach((path) -> {
                    try {
                        //String path = rs.getString(1);
                        try {
                            AudioPlayer.operate(path);
                        } catch (UnsupportedAudioFileException e) {
                            e.printStackTrace();
                        } catch (LineUnavailableException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                });

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}