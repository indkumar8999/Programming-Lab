import java.util.*;
import java.lang.*;

class Main {
  
  	public int collectedSocks;
  	
  	public static void main(String[] args) throws Exception{
    
    Sock[] listofsocks = new Sock[10];
    String colors[] = new String[]{"white","black","blue","grey"};
    for(int i=0;i<10;i++)
    {
        Random rand = new Random();
        int x = rand.nextInt(4)+1;
        listofsocks[i] = new Sock(x, i);
        System.out.println("color of "+i+" is "+colors[x-1]);
    }
    
    Robot[] robots = new Robot[4];
   	
    MatchingMachine machine = new MatchingMachine().getInstance();
    
      for(int i=0;i<4;i++)
      {
        if(machine.q.size() < 10)
        {
          robots[i] = new Robot(listofsocks, i);
          robots[i].start();
        }
      }
      machine.start();
  }
}