import java.io.*;
import java.lang.*;
import java.util.*;

class Sample
{
    public static int numberOfSocks = 5;
    public static List<Sock> ListOfsocks;
    public static int numberOfRobotArms = 3;
    public static List<RobotArm> ListOfRobotArms;
    public static List<Thread> ListOfThreads;
    public static String[] colors = new String[]{"white", "black", "green", "blue"};

    public static void main(String []args)
    {
        ListOfsocks = new ArrayList<>();
        ListOfRobotArms = new ArrayList<>();
        ListOfThreads = new ArrayList<>();

        for(int index=0; index < numberOfSocks; index++)
        {
            Random r = new Random();
            int color_index = r.nextInt(4);
            String sock_color = colors[color_index];
            Sock sock = new Sock(sock_color);

            ListOfsocks.add(sock);
        }

        for(int index=0; index < numberOfRobotArms; index++)
        {
            RobotArm robot = new RobotArm(ListOfsocks);
            Thread new_thread = new Thread(robot);
            new_thread.start();
            ListOfRobotArms.add(robot);
            ListOfThreads.add(new_thread);
        }

        

        
    }
}