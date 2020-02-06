/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bab;

import bab.Kapcsolat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bendeati
 */
public class Login {
    public int id;
   
    public String username;
    public String password;
    public String fullname;
    public String email;
    
    public int kuldo;
    public int baratok;
    
    public final Kapcsolat kapcs;
    public String uzenet;
    public Statement st;
    public ResultSet rs;
    public Connection con;
       
    private boolean loggedIn;

    public Login() {
        this.loggedIn = false;
        kapcs = new Kapcsolat();
    }

    //Getterek és Setterek
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public boolean login(String url)
    {
        try{
         // open a connection
          Class.forName("com.mysql.jdbc.Driver");
          if(kapcs.conOK())
          {
            con = kapcs.getCon();
            st = kapcs.getStm();
            rs = st.executeQuery("select * from szemelyek WHERE felhasz LIKE '"+username+"' AND jelszo LIKE '"+password+"'");

            if(rs.next())
            {
             username = rs.getString("felhasz");
             password = rs.getString("jelszo");
             id = rs.getInt("id");
             loggedIn = true;
            }
            else
              loggedIn = false;
            st.close();
            con.close();
          }
         }
        catch(ClassNotFoundException | SQLException e)
        {
          loggedIn = false; 
        } 
       
        return loggedIn;
   }
    public void Adatokmodosit(int id)
    {
        
        try 
        { 
           
            String query = "UPDATE `szemelyek` SET "
                    + "nev = '"+fullname+"',"
                    + "felhasz = '"+username+"',"
                    + "email = '"+email+"'  WHERE id="+id;
            System.out.println(query);
            st.executeUpdate(query); 
            System.out.println("A módosítás sikeres!");
        }
        catch (SQLException e)
        {
            System.out.println("Hibás rögzítés:" + e.getMessage());
        }
    }
      public void JelszoMod(int id)
    {
        
        try 
        { 
          
            String query = "UPDATE `szemelyek` SET "
                    + "jelszo = '"+password+"',"
                    + "felhasz = '"+username+"',"
                    + "email = '"+email+"'  WHERE id="+id;
            st.executeUpdate(query); 
            System.out.println("A módosítás sikeres!");
        }
        catch (SQLException e)
        {
            System.out.println("Hibás rögzítés:" + e.getMessage());
        }
    }
       public void mindenki()
    {
       try 
        {
            if (kapcs.conOK()) 
            {
                st = kapcs.getStm();
                rs = st.executeQuery("select * from szemelyek");
            }
          
        }
        catch (SQLException e) {
             uzenet = "Hibás adatlekérés! " + e;
        }
    }
   public void barat()
    {
       try 
        {
            if (kapcs.conOK()) 
            {
                st = kapcs.getStm();
                rs = st.executeQuery("select * from szemelyek");
            }
          
        }
        catch (SQLException e) {
             uzenet = "Hibás adatlekérés! " + e;
        }
    }
       public void baratnakJelol(int kuldo, int baratok)
    {
        try 
        { 
           
            if(kapcs.conOK())
            {
                String query = "INSERT INTO `bartok`(`kuldo`, `baratok`) VALUES ('"
                    +kuldo+"','"+baratok+"')";
                st = kapcs.getStm();
                st.executeUpdate(query); 
                
            }
            
        }
        catch (SQLException e)
        {
            uzenet = "Sikertelen barát kérelem!" + e.getMessage();
            
        }
    }
        
    public void barataim(int id)
    {
        try 
        { 
            
            if(kapcs.conOK())
            {
                String query = "SELECT * FROM `bartok` inner join szemelyek on bartok.kuldo = szemelyek.id where kuldo = "+id;
                st = kapcs.getStm();
                rs = st.executeQuery(query);   
            }
            
        }
        catch (SQLException e)
        {
            uzenet = "Sikertelen barát kérelem!" + e.getMessage();
            
        }
    }
         
        public void adataim(int id)
    {
       try 
        {
            if (kapcs.conOK()) 
            {
                st = kapcs.getStm();
                rs = st.executeQuery("select * from szemelyek where id ="+id);
            }
          
        }
        catch (SQLException e) {
             uzenet = "Hibás adatlekérés! " + e;
        }
    }
         
   public void logOut()
   {
     loggedIn = false;
   }
   
  public ResultSet listaLeker() {  
      return rs;  
    }
}
