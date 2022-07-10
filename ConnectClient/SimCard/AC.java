import java.io.*;
import java.util.*;
import java.net.*;
import java.awt.geom.Point2D;


class Payload implements Serializable{
  final private String phone_number;
  final private String secret_key;
  private String access_token = null;
  private transient Point2D.Double geolocation = new Point2D.Double(new Random().nextDouble()*100,new Random().nextDouble()*100);
  

  
  Payload(String phone_number, String secret_key){
     this.phone_number = phone_number;
     this.secret_key  = secret_key;
  }

  public String getPhone_number() {
      return phone_number;
  }

  
  public String getSecret_key() {
      return secret_key;
  }

 
  public String getAccess_token() {
      return access_token;
  }

  public void setAccess_token(String access_token) {
      this.access_token = access_token;
  }

  public Point2D.Double getGeolocation() {
      return geolocation;
  }




}

///////////////////////////////////////////////////////////////Server Thread

class ServerThread extends Thread{

    private Socket socket = null;
    
	public ServerThread(Socket socket){
		super("ServerThread");
		this.socket = socket;
	}
	
	public void run(){
		try{
			OutputStream o = socket.getOutputStream();
			PrintWriter output = new PrintWriter(o, true);
            InputStream i = socket.getInputStream();
            

            Scanner input = new Scanner(i);
			
			output.println("I am server to client");
            String data = input.next();
            

            System.out.println(data);

            input.close();
		}catch(IOException e){
			System.err.println(e);
			System.exit(1);
        }
        finally{
			try{
				socket.close();
			}catch(IOException e){
				System.err.println(e);
				System.exit(10);
			}
		}
	}
}


///////////////////////////////////////////********************************************************************************* */
class AC {
    //stores the payloads of connected sim
    private ArrayList<Payload> payloads; //= new ArrayList<Payload>();
    private String ID = new String("xyz");
    private int port = 1234;
    private ServerSocket socket = null;


    public void activate() throws IOException {
		try{
			socket = new ServerSocket(port);
		}catch(IOException e){
			System.err.println(e);
			System.exit(1);
		}
		
		while(true){
			Socket conn = socket.accept();
			ServerThread st = new ServerThread(conn);
			st.start();
		}
	}

    public String getID() {
        return ID;
    }


    public ArrayList<Payload> getPayloads() {
        return payloads;
    }


    public int getPort() {
        return port;
    }

    public ServerSocket getSocket() {
        return socket;
    }

    public static void main(String[] args) throws IOException{
        //Payload p = new Payload("33434", "secret_key");
        AC ac = new AC();
        ac.activate();
     }

}





