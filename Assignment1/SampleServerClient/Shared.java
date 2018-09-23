import java.util.*;
import java.lang.*;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;


class Shared extends Thread
{
	public static Shared s;
	public static int cookies = 100, snacks = 100;
    public static ArrayList<ClientInfo> q;
    public static Semaphore sema;
	Shared(Semaphore sema)
	{
        this.sema = sema;
		q = new ArrayList<ClientInfo>();
	}
	public static Shared getInstance(){
		if(s == null){
			s = new Shared(MainServer.sema);
			return s;
		}else{
			return s;
		}
    }
    
    public void run()
    {
        // System.out.println("fjasdfkjahfkjahskjfhaskjfhasdkjhgsajdhgakjsdhgk");
        while(true)
        {
            // Date d = new Date();
            // System.out.println(d.toString());
            try{
                MainServer.sema.acquire();
                if(!Shared.getInstance().q.isEmpty()){
                ClientInfo cl = new ClientInfo(Shared.getInstance().q.get(0));
                MainServer.sema.release();
                // System.out.print(cl.rollno + " order is removed now at ");
                // Date d = new Date();
                // System.out.println(d.toString());
                // System.out.println(cl.rollno + " order is being processsed ");
                Thread.sleep((cl.processing_time)*1000);
                MainServer.sema.acquire();
                Shared.getInstance().q.remove(0);
                MainServer.sema.release();
                System.out.println(cl.rollno + " order is left for transit at");
                System.out.print(cl.rollno + " order is delivered at ");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");

                System.out.println(sdf.format(cl.estimated_time.getTime()));
                }else{
                    MainServer.sema.release();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
