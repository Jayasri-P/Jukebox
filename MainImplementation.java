import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MainImplementation {

    static String login_status = null;

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException {
        Scanner sc = new Scanner(System.in);
        int a = 0;

        System.out.println("***************************Welcome to Jukebox*****************************");
        Home home = new Home();
        home.page1();
        home.page2();

        do {
            System.out.println("Choose an option you would like to prefer\n" +
                    "1. to view and play music/podcast\n" +
                    "2. to create  playlist\n" +
                    "3. to view the playlist created");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Play listen = new Play();
                    listen.option();
                    System.out.println("*********************************************************************");
                    break;
                case 2:
                    UserLogin signing = new UserLogin();
                    if (login_status != "loggedIn") {
                        signing.option();
                        System.out.println("------------------------------------------------------------------------");
                    }
                    if (login_status == "loggedIn") {
                        NewPlayList playListCreate = new NewPlayList();
                        playListCreate.create_playList();
                        System.out.println("----------------------------------------------------------------------");
                    }
                    break;
                case 3:
                    PlayListclass playThePlayList = new PlayListclass();
                    playThePlayList.play_playlist();
                    System.out.println("-----------------------------------------------------------------------");
                    break;
            }
            System.out.println("Do you want to proceed?\n1.Yes\n2.No");
            a = sc.nextInt();
            System.out.println("***************************************************************************");
        }
        while (a == 1);

    }
}