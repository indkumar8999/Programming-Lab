import java.util.*; 
import java.lang.*;
import java.io.*;

class MatchingMachine extends Thread{
  
  int color1;
  int color2;
  int color3;
  int color4;
  
  public static MatchingMachine m ;
  public static LinkedList<Sock> q;
  public static int matchedSocks;
  public MatchingMachine()
  {
    color1 = -1;
    color2 = -1;
    color3 = -1;
    color4 = -1;
    matchedSocks = 0;
    q = new LinkedList<Sock>();
  }

  public static MatchingMachine getInstance()
  {
    if(m == null){
      m = new MatchingMachine();
      matchedSocks = 0;
      return m;
    }else{
      return m;
    }
  }

  public void run()
  {
    // while(true)
    // {
    //   System.out.println("queue size : " + this.q.size());
    //   if(!q.isEmpty() && q.size() < 10){
    //     // System.out.println("matching code");
    //     // System.out.println("queue size : " + this.q.size());
    //     Random rand = new Random();
    //     int x = rand.nextInt(q.size());
        
    //     }
    //   else if(q.size() == 10){
    //     Match();
    //     break;
    //   }
    // }

    while(MatchingMachine.matchedSocks <= 10)
    {
      //System.out.println("Dilip");
      if(q.size() != 0){
        Random rand = new Random();
        int x = rand.nextInt(q.size());
        
        if(q.get(x).collected == 0)
        {
          MatchingMachine.matchedSocks++;
          int color = q.get(x).color;
          switch(color)
          {
            case 1:
              if(color1 != -1){
                System.out.println("Matcher matched socks "+q.get(color1).index+" and "+q.get(x).index);
                color1  = -1;
                q.get(x).collected = 1;
              }else{
                color1 = x;
                q.get(x).collected = 1;
              }
              break;
            case 2:
              if(color2 != -1){
                System.out.println("Matcher matched socks "+q.get(color2).index+" and "+q.get(x).index);
                q.get(x).collected = 1;
                color2 = -1;
              }else{
                color2 = x;
                q.get(x).collected = 1;
              }
              break;
            case 3:
              if(color3 != -1){
                System.out.println("Matcher matched socks "+q.get(color3).index+" and "+q.get(x).index);
                q.get(x).collected = 1;
                color3  = -1;
              }else{
                color3 = x;
                q.get(x).collected = 1;
              }
              break;
            case 4:
              if(color4 != -1){
                System.out.println("Matcher matched socks "+q.get(color4).index+" and "+q.get(x).index);
                q.get(x).collected = 1;
                color4  = -1;
              }else{
                color4 = x;
                q.get(x).collected = 1;
              }
              break;
            default:
              System.out.println("This case never comes");
          }
        }
        if(MatchingMachine.matchedSocks == 10)
          break;
      }
    }
  }

  public synchronized void Push(Sock s)
  {
    q.add(s);
    //System.out.println("queue size : " + this.q.size());
  }
  
  public void Match()
  {
    
  }
  
}