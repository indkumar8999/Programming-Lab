import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.*;

class Robot extends Thread {
  	Sock sock;
    int sockIndex;
    Sock[] a;
    int index;
  	@Override
  	public void run()
    {
      	
          try {
            selectSock();
          }catch(Exception e){
            //System.out.println("exception occured "+ e);
          }finally{
            //run();
          }
      	
    }
  
  Robot(Sock a[], int i)
  {
    this.a = a;
    sock = null;
    sockIndex = -1;
    index = i;
  }
  public void selectSock() throws Exception
  {

    while(MatchingMachine.q.size() < 10)
    {
      Random rand = new Random();
      int x = rand.nextInt(10);
      ReentrantLock l = new ReentrantLock();
      if(a[x].getLock())
      {
        try{
          if(a[x].collected == 0)
          {
            sock = new Sock(a[x]);
            sockIndex = x;
            a[x].collected = 1;
            sock.collected = 0;
            sock.index = x;
            System.out.println(this.index+" Thread selected sock "+x);
            MatchingMachine.getInstance().Push(sock);
          }else{
            System.out.println(this.index+" Thread tried to access "+x+" but access denied");
          }
        }catch(Exception e){
          
        }finally{
          a[x].unLock();
        }
      }
    }
  }

}
