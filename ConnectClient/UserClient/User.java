import java.io.*;
import java.util.*;
import java.net.*;


public class User implements Serializable{
  private String username;
  private String email;
  private transient String password;
  private Date reg_date;
  private int age;

  
  public User(String username, String email,String password,int age){
     this.username = username;
     this.email = email;
     this.password = password;
     this.age = age;
     reg_date = new Date();
  }


  //serialization
  private void writeObject(ObjectOutputStream out) throws IOException{
    //write nontransient fields
    out.defaultWriteObject();
    //write transient fields
    out.writeUTF(password);
     }

  //deserialization
  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
    //read non-transient fields
    in.defaultReadObject();
    //read transient
    in.readUTF();
  }


  public int getAge() {
      return age;
  }


  public String getEmail() {
      return email;
  }


  public String getPassword() {
      return password;
  }


  public Date getReg_date() {
      return reg_date;
  }

  public String getUsername() {
      return username;
  }

  @Override
  public String toString() {
      return username + ", " + email +  ", " + password + ", " + age + ", " + reg_date;
  }

  @Override
  public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || !(getClass() == object.getClass())) {
          return false;
      }

      User user = (User) object;
      return username.equals(user.username) && email.equals(user.email);

  }

  @Override
  public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((username == null) ? 0 : username.hashCode());
      return result;
  }


}

  
