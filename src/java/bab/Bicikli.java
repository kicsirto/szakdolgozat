/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author besse
 */
public class Bicikli {
    public int id;
    public String marka;
    public String tipusszam;
    public String egyeb;
    public int Userid;
    
    private final Kapcsolat kapcs;
     
    private String uzenet;
    private PreparedStatement prSt;
    private ResultSet rs;
    private Statement st;
    private Connection con;
    
    public Bicikli() {
        this.kapcs = new Kapcsolat();
    }
    
    public ResultSet listaLeker() {  
      return rs;  
    }

    
    public void adatok(int Userid) 
    {
        try 
        {
            if (kapcs.conOK()) 
            {
                st = kapcs.getStm();
                rs = st.executeQuery("SELECT * FROM `bicikli` where kiheztartozik="+Userid);
            }
          
        }
        catch (SQLException e) {
             uzenet = "Hibás adatlekérés! " + e;
        }
    }
    
    
    
     public void modosit(int Userid)
    {
        
        try 
        { 
           
            String query = "UPDATE bicikli SET "
                    + "marka = '"+marka+"',"
                    + "tipusszam = '"+tipusszam+"'  WHERE kiheztartozik="+Userid;
            st.executeUpdate(query); 
            System.out.println("A módosítás sikeres!");
        }
        catch (SQLException e)
        {
            System.out.println("Hibás rögzítés:" + e.getMessage());
        }
    }
     
      
       
     public boolean torles(int Userid)
    {
        boolean siker = false;
        try 
        { 
            String query = "DELETE FROM `bicikli` WHERE id="+Userid;
            st.executeUpdate(query); 
            siker = true;
        }
        catch (SQLException e)
        {
            System.out.println("Hiba a mátrixban:" + e.getMessage());
        }
        return siker;
    }
     
      public void bicikliInsert()
    {
        try 
        { 
            if(kapcs.conOK())
            {
                String query = "INSERT INTO `bicikli`( `marka`, `tipusszam`, `egyeb`, `kiheztartozik`) VALUES('"
                    +marka+"','"+tipusszam+"','"+egyeb+"','"+Userid+"')";
                st = kapcs.getStm();
                st.executeUpdate(query); 
                uzenet = "A bicikli regisztrációja sikeres!";
                
            }
            
        }
        catch (SQLException e)
        {
             System.out.println("Hiba a mátrixban:" + e.getMessage());
        }
        
    }
     
      
  /*
   
    
    public void kirandulas()
    {
        try
        {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = kapcs.getCon();
        Statement st=conn.createStatement();
        int i= st.executeUpdate("INSERT INTO `kirandulasok`(`ki`, `hossz`, `vegcel`, `indulas`) VALUES ("+id+", "+hossz+", '"+vegcel+"','"+indulas+"')");
        System.out.println("Data Deleted Successfully!");
        }
        catch(Exception e)
        {
        System.out.print(e);
        }
    }
    public void osszKirandulas()
    {
        try 
        {
            if (kapcs.conOK()) 
            {
                st = kapcs.getStm();
                rs = st.executeQuery("SELECT * FROM `kirandulasok`");
            }
            if (rs.next()) 
            {
                szervezo = rs.getInt("ki");
                hossz = rs.getInt("hossz");
                vegcel = rs.getString("vegcel");
                indulas = rs.getString("indulas");
                mikor = rs.getString("mikor");
            } 
            else 
            {
                System.out.println("valami gond van");
            }
        } catch (Exception e) 
        {
            System.out.println("hát ja"+e);
        }
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getTipusszam() {
        return tipusszam;
    }

    public void setTipusszam(String tipusszam) {
        this.tipusszam = tipusszam;
    }

    public int getUid() {
        return Userid;
    }

    public void setUid(int Uid) {
        this.Userid = Uid;
    }
    }
    


