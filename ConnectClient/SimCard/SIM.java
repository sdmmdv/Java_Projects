import java.io.*;
import java.util.*;
import java.net.*;

////////////////////////////////////////////************* Sim Class */

class SIM {
    private Payload payload;
    private Socket socket = null;
    private InetAddress ip;
    private int port = 1234;
 
 
    public SIM(){
       //initalize later getting input
       Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        String str2 = scanner.next();
       //payload = new Payload("phone_number", "secret_key");
       payload = new Payload(str1, str2);
       scanner.close();
    }
 
    public Payload getPayload() {
        return payload;
    }
 
    public void connect() throws IOException{
     ip = InetAddress.getByName("127.0.0.1");

     //create socket
     socket = new Socket(ip, port);
     
     
     try{
         Scanner input = new Scanner(socket.getInputStream());
         PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
         String data = input.next();
         System.out.println("Send Payload details");

         output.write(payload.getPhone_number() + " " + payload.getAccess_token());
         output.println("sent!"); // send data to the server
     }catch(IOException error){
         System.err.println(error);
         System.exit(1);
 
       }finally{
         socket.close();
       }
     }

     public static void main(String[] args) throws IOException{
		SIM sim = new SIM();
		sim.connect();
	}
 
 
 
 }

