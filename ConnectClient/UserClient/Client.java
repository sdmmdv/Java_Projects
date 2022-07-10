import java.io.*;
import java.util.*;
import java.net.*;



public class Client {
    private String name;
    private Socket socket = null;
    private int port = 1234;
    private InetAddress ip;


    public void connect() throws IOException{
		ip = InetAddress.getByName("127.0.0.1"); // localhost
		socket = new Socket(ip, port); // trying to connect to the server
		
		try{
			Scanner input = new Scanner(socket.getInputStream());
      PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
      
			String data = input.next(); // read data received from server
			System.out.println(data); 
            System.out.println("Connection to server established.");
            output.println("Hi!");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the username: ");
            name = scanner.nextLine();
            output.println(name); // send name to the server

            System.out.println("Enter the email: ");
            output.println(scanner.nextLine());

            System.out.println("Enter the password: ");
            output.println(scanner.nextLine());

            System.out.println("Enter the age: ");
            output.println(scanner.next());

            scanner.close();

		}catch(IOException e){
			System.err.println("Could not connect to host on port " + port);
			System.exit(1);
		}finally{
			socket.close();
		}
	}
	
	public static void main(String[] args) throws IOException{
		Client client = new Client();
		client.connect();
	}





}

