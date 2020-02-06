/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author besse
 */
public class Kapcsolat {
    private Connection con;
    private Statement stm;
    private String uzenet;
    
    public Kapcsolat(){
    }
    
    public Statement getStm(){
        return stm;
    }
    public boolean conOK(){
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/szakdoga?useUnicode=yes&characterEncoding=UTF-8","root","");
            stm = con.createStatement();
            return  true;
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            uzenet=e.toString();
            return  false;
        }
    }
    public String uzenet()
    {
        if (conOK())        
        return "Kapcsolat oké";
        else
            return "Hiba a kapcsolatban <br>"+uzenet;
    }
    
    public Connection getCon() {
        return con;
    }
}
