import java.util.*;
import java.util.concurrent.Semaphore;
import java.lang.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

class ServerThread extends Thread
{
    Socket s;
    Server server;
    DataInputStream input;
    DataOutputStream output;
    Semaphore sema;
    ServerThread(Semaphore sema, Socket s, Server server)
    {
        this.s = s;
        this.sema = sema;
        this.server = server;
    }

    public void run()
    {
        try{
            input = new DataInputStream(s.getInputStream());
            output = new DataOutputStream(s.getOutputStream());
        
            String sentence = input.readUTF();
            //output.writeUTF(String.format(sentence));
            String a[] = sentence.split(" ");
            System.out.println(a[0]);
            System.out.println(a[1]);
            System.out.println(a[2]);
            System.out.println(a[3]);
            System.out.println(a[4]);
            System.out.println(a[5]);
            ClientInfo new_client = new ClientInfo(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2]), Integer.parseInt(a[3]), Integer.parseInt(a[4]), Integer.parseInt(a[5]));
            sema.acquire();
            int flag = 0;
            if(new_client.cookies <= Shared.cookies && new_client.snacks <= Shared.snacks)
                flag = 1;
            if(flag == 1)
            {
                Shared.cookies -= new_client.cookies;
                Shared.snacks -= new_client.snacks;
                // new_client.estimated_time += new_client.tea;
                // new_client.estimated_time += new_client.coffee;
                // new_client.estimated_time += 2;
                // for(int i=0;i<Shared.getInstance().q.size();i++){
                //     new_client.estimated_time += (Shared.getInstance().q.get(i).processing_time) ;
                // }
                Shared.getInstance().q.add(new_client);

                // new_client.estimated_time = MainServer.global + new_client.processing_time + 2;
                // MainServer.global += new_client.processing_time;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
                new_client.estimated_time = (MainServer.calendar.compareTo(new_client.estimated_time.getInstance()) > 0)? MainServer.calendar : new_client.estimated_time.getInstance(); 
                MainServer.calendar = (Calendar)new_client.estimated_time.clone();
                
                new_client.estimated_time.add(Calendar.MINUTE, 2);
                MainServer.calendar.add(Calendar.MINUTE, new_client.processing_time);
                new_client.estimated_time.add(Calendar.MINUTE, new_client.processing_time);

                // Date d = new Date();
                // System.out.print(new_client.rollno + " order is received now at");
                // System.out.println(d.toString());
                
                output.writeUTF(sdf.format(new_client.estimated_time.getTime())+" is the estimated time at which it will be received");
                // output.writeUTF(new_client.estimated_time + " is the estimated time at which it will be received");
                
                // d = new Date();
                // System.out.println(d.toString());
            }else{
                output.writeUTF("The order is not possible");
            }
            
            sema.release();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception occured");
        }finally{
            try{
                input.close();
                output.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}