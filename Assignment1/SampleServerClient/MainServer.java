import java.util.*;
import java.lang.*;
import java.net.ServerSocket;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.Calendar;

public class MainServer
{

	public static Date d = new Date();
	public static Calendar calendar = Calendar.getInstance();
	// calendar.setTime(d);

	// public static int global = 0;
	public static Semaphore sema = new Semaphore(1);
	public static void main(String[] args) {
		Shared s = new Shared(sema).getInstance();
		s.start();

		Server server = new Server();
		server.startServer();

		
	}
}