/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bab;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bendeati
 */
public class Register {
    public String username;
    public String password;
    public String fullname;
    public String email;
    public int jog = 2;
    
    
    private boolean registerOK;
    
    private final Kapcsolat kapcs;
    private String uzenet;
    private Statement st;
    private ResultSet rs;
    

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

    /*public void setPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
        this.password = myHash;
    }*/

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUzenet() {
        return uzenet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    
    
    public Register() {
        this.kapcs = new Kapcsolat();
    }
    
    public boolean isUserInDatabase()
    {
        try 
        {
            if(kapcs.conOK())
            {
                st = kapcs.getStm();
                rs = st.executeQuery("select * from szemelyek WHERE felhasz LIKE '"+username+"'");
                if(rs.next())
                {
                    uzenet = "Létező felhasználó!";
                    return false;
                }
                else
                {
                    return true;
                }
            }
        } 
        catch (SQLException e) 
        {
            uzenet = "Hiba a lekérdezéskor" + e;
        }
        return true;
    }
    
    public void userInsert()
    {
        try 
        { 
            if(kapcs.conOK() && isUserInDatabase())
            {
                String query = "INSERT INTO szemelyek(`nev`, `email`, `felhasz`, `jelszo`, `jog`) values('"
                    +fullname+"','"+email+"','"+username+"','"+password+"','"+jog+"')";
                st = kapcs.getStm();
                st.executeUpdate(query); 
                st.executeQuery("SET CHARACTER SET 'UTF8'");
                uzenet = "A regisztráció sikeres! Jelentkezzen be!";
                registerOK = true;
            }
            
        }
        catch (SQLException e)
        {
            uzenet = "Sikertelen adatrögzítés!" + e.getMessage();
            registerOK = false;
        }
        
    }
     
}
