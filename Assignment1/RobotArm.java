import java.io.*;
import java.lang.*;
import java.util.*;

class RobotArm implements Runnable
{
    public List<Sock> SocksList;
    public Sock CapturedSock;
    RobotArm(List<Sock> SocksList)
    {
        this.SocksList = SocksList;
        this.CapturedSock = null;
    }
    
    @Override
    public void run()
    {

    }

    public void pickSock()
    {

    }

    public void dropSock()
    {
        
    }
}