import java.net.*;
import java.io.*;

public class ChatClient implements Runnable{
   private Socket socket = null;
   private Thread thread = null;
   private DataInputStream input = null;
   private DataOutputStream output = null;
   private ChatClientThread client = null;

   public ChatClient(String serverName, int serverPort){ 
	  System.out.println("Establishing connection. Please wait ...");
      try
      {  
    	  socket = new Socket(serverName, serverPort);
    	  System.out.println("Connected: " + socket);
    	  start();
      }
      catch(UnknownHostException uhe){  
    	  System.out.println("Host unknown: " + uhe.getMessage()); 
      }
      catch(IOException ioe){  
    	  System.out.println("Unexpected exception: " + ioe.getMessage()); 
      }
   }
   
   public void run(){  
	   while (thread != null){  
		 try
         {  
			output.writeUTF(input.readLine());
            output.flush();
         }
         catch(IOException ioe){
        	 System.out.println("Sending error: " + ioe.getMessage());
        	 stop();
         }
      }
   }
   
   
   public void handle(String msg){  
	   if (msg.equals(".bye"))
	   {
		   System.out.println("Bye! Press ENTER to exit...");
		   stop();
	   }
      else
         System.out.println(msg);
   }
   
   
   public void start() throws IOException{
	   input = new DataInputStream(System.in);
	   output = new DataOutputStream(socket.getOutputStream());
	   
	   if(thread == null){
		   client = new ChatClientThread(this, socket);
		   thread = new Thread(this);
		   thread.start();
	   }
   }
   
   
   public void stop(){
	   if (thread != null){
		   thread.stop();
		   thread = null;
	   }
	   try{
		   if (input != null)
			   input.close();
		   if (output != null)
			   output.close();
		   if (socket != null)
			   socket.close();
	   }
	   catch(IOException ioe){
		   System.out.println("Error closing...");
	   }
	   
	   client.close();  
	   client.stop();
   }
   
   
   public static void main(String args[]){
	   ChatClient client = null;
	   client = new ChatClient("localhost", 6789);
   }
}