import java.util.*;
import java.net.*;
import java.io.*;


public class Server {
   private ArrayList<User> users;
   private int port;
   private ServerSocket socket;
   private InetAddress ip;

   public Server(){
      users = new ArrayList<User>();
      port = 1234;
      socket = null;
      
   }

   public ArrayList<User> getUsers() {
       return users;
   }

   public void addUser(User user){
       users.add(user);
   }

   public void activate() throws IOException {
     ip = InetAddress.getByName("127.0.0.1"); //ip listens localhost
     try{
         socket = new ServerSocket(port);
     }catch(IOException e){
         System.err.println("Cannot listen to " + port);
         System.exit(1);
     }


     while(true){
          Socket conn = socket.accept();
          ServerThread st = new ServerThread(conn);
          st.start();            
      }

   }  

   class ServerThread extends Thread{
	private Socket socket = null;
	public ServerThread(Socket socket){
		super("ServerThread");
		this.socket = socket;
    }

    public void serialize(){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("out.dat")); 
            out.writeObject(users); 
            out.close();
         }catch(IOException e){
            System.out.println("IO Exception!!");
         }
    }

    public void deSerialize(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("out.dat"));
            //suppress unchecked or unsafe operations for custom class types
            @SuppressWarnings("unchecked")
            ArrayList<User> read_users = (ArrayList) in.readObject(); // 
  
            int index = new Random().nextInt(2); 
            //System.out.println(read_users.toString());
    
            if(users.get(index).equals(read_users.get(index))){
               System.out.println("Users successfully stored");
            }
            else{
                System.out.println("Unsuccessfull attempt!");
            }
            
  
          }catch(ClassNotFoundException e){
              System.out.println("ClassNotFound Exception!!");
          }catch(IOException e){
              System.out.println("IO Exception!!");
          }catch(Exception e){
              e.printStackTrace();
          }  
    }
    
	
	public void run(){
		try{
			OutputStream o = socket.getOutputStream();
			PrintWriter output = new PrintWriter(o, true);
			InputStream is = socket.getInputStream();
            Scanner input = new Scanner(is);
			
			output.println("Hi!"); // sent to the client
			String data = input.next(); // get data from the client
            System.out.println(data); // do some work with the data
            String client_name = input.next();
            System.out.println("Connection to " + client_name + " established");

            //receive user details
            String email = input.next();
            String password = input.next();
            int age = input.nextInt();

            System.out.println("Received User -> [name: " + client_name + ", email: " + email + ", age: " + age + "]");
            
            //user = new User(client_name, email, password, age);
            users.add(new User(client_name, email, password, age));
            input.close();
            
            // print users list
            // for(User u : users){
            //     System.out.println(u.toString());
            // }

		}catch(IOException e){
			System.err.println("IO exception!!");
			System.exit(1);
		}finally{
			try{
				socket.close();
			}catch(IOException e){
				e.printStackTrace();
				System.exit(2);
			}
        }
        

        if(users.size() == 2) {
            serialize();
            deSerialize();
        }

	}
}


    public static void main(String[] args) throws IOException{
        Server server = new Server();
        server.activate();
    }


}

